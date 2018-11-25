package lk.ijse.dinemore.controller.chef;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ChefTakeOrderController {

    @FXML
    private TableView<OrderDTO> orderTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private TableView<MealChefDTO> mealTable;

    @FXML
    private JFXButton takeOrderButton;

    @FXML
    private JFXButton cancelOrderbutton;

    @FXML
    private JFXButton finishedOrderButton;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Label endTimeLabel;


    private ChefQueueService chefQueueService;
    private OrderService orderService;
    private MealService mealService;
    private CookingService cookingService;
    private LogInService logInService;
    private ChefService chefService;
    private OrderDetailService orderDetailService;

    private ArrayList<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
    private OrderDetailDTO lastChefQueue = new OrderDetailDTO();
    private Date startDate;
    private int chefId;
    private int orderId;
    private LinkedList<OrderDetailDTO> allChefQueue;
    private OrderDetailDTO que = new OrderDetailDTO();

    public void initialize() {
        try {
            logInService = (LogInService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.LOG_IN);
            chefQueueService = (ChefQueueService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEF_QUEUE);
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDERS);
            mealService = (MealService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.MEAL);
            cookingService = (CookingService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.COOKING);
            chefService = (ChefService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEF);
            orderDetailService = (OrderDetailService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDateTime();
//        loadOrders();
        loadColumns();
        loadMealColumns();

        new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    loadOrders();
                }
            }, new Date(), 2000);
        }).start();
        getChefId();

    }

    private void getChefId() {
        try {
            ArrayList<LogInDTO> allLogin = (ArrayList<LogInDTO>) logInService.getAllLogin();
            for (LogInDTO l : allLogin) {
                chefId = l.getUserId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMealColumns() {
        mealTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mealName"));
        mealTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("mealCategory"));
        mealTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void loadColumns() {
        orderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }

    private void loadOrders() {
        orderTable.getItems().clear();
        try {
            allChefQueue = (LinkedList<OrderDetailDTO>) chefQueueService.getAllChefQueue();
            if(allChefQueue != null) {
                ArrayList<OrderDTO> allOrder = (ArrayList<OrderDTO>) orderService.getAllOrder();
                orderDetailDTOS.clear();
                for (int i = allChefQueue.size() - 1; i >= 0; i--) {
                    orderDetailDTOS.add(allChefQueue.get(i));
                }

                ArrayList<OrderDTO> orderDTOArrayList = new ArrayList<>();
                for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
                    for (OrderDTO orderDTO1 : allOrder) {
                        if (orderDetailDTO.getOrderDTO().getOrderId() == orderDTO1.getOrderId()) {
                            orderId = orderDTO1.getOrderId();
                            OrderDTO orderDTO = new OrderDTO(orderDTO1.getOrderId(), orderDTO1.getOrderDate(), orderDTO1.getCustomerDTO(), orderDTO1.getOrderDetailDTOS(), orderDTO1.getOperatorDTO());
                            orderDTOArrayList.add(orderDTO);
                        }
                    }
                }
                ObservableList<OrderDTO> orderDTOS = FXCollections.observableArrayList(orderDTOArrayList);
                orderTable.setItems(orderDTOS);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setDateTime() {
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
    }

    @FXML
    void cancelOrderButtonAction(ActionEvent event) {
//        System.out.println(lastChefQueue.getOrderDetailId());
        try {
            chefQueueService.addLastChefQueue(lastChefQueue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mealTable.getItems().clear();
        takeOrderButton.setDisable(true);
        cancelOrderbutton.setDisable(true);
        finishedOrderButton.setDisable(true);
        orderTable.setDisable(false);
    }

    @FXML
    void finishedOrderButtonAction(ActionEvent event) {
        try {
            ArrayList<ChefDTO> allChef = (ArrayList<ChefDTO>) chefService.getAllChef();
            ChefDTO chefDTO = new ChefDTO();
            for (ChefDTO chef : allChef) {
                if (chef.getChefId() == chefId) {
                    chefDTO = new ChefDTO(chefId, chef.getChefName(), chef.getChefNIC(), chef.getChefAddress(), chef.getChefUserName(), chef.getChefPassword());
//                    chefDTO.setChefId(chefId);
//                    chefDTO.setChefName(chef.getChefName());
//                    chefDTO.setChefNIC(chef.getChefNIC());
//                    chefDTO.setChefAddress(chef.getChefAddress());
//                    chefDTO.setChefUserName(chef.getChefUserName());
//                    chefDTO.setChefPassword(chef.getChefPassword());
                }
            }
            ArrayList<OrderDetailDTO> allOrderDetail = (ArrayList<OrderDetailDTO>) orderDetailService.getAllOrderDetail();
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            for (OrderDetailDTO orderDetailDTO1 : allOrderDetail) {
                if (orderDetailDTO1.getOrderDTO().getOrderId() == orderId) {
                    orderDetailDTO = new OrderDetailDTO(orderDetailDTO1.getOrderDetailId(), orderDetailDTO1.getOrderdQty(), orderDetailDTO1.getPrice(), orderDetailDTO1.getOrderDTO(), orderDetailDTO1.getMealDTO());
                }
            }


//            System.out.println(chefDTO.getChefId());
//            System.out.println(orderDetailDTO.getOrderDetailId());
//            System.out.println(new CookingDTO(startDate, new Date(), chefDTO, orderDetailDTO).getStartTime());
            boolean b = cookingService.addCooking(new CookingDTO(startDate, new Date(), chefDTO, orderDetailDTO));
            if (b) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Order Finished!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                disableControl();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void disableControl() {
        cancelOrderbutton.setDisable(true);
        finishedOrderButton.setDisable(true);
        mealTable.getItems().clear();
        orderTable.setDisable(false);
    }

    @FXML
    void orderTableClicked(MouseEvent event) {
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "you should select first!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            OrderDTO selectedItem = orderTable.getSelectionModel().getSelectedItem();
            for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
                if (orderDetailDTO.getOrderDTO().getOrderId() == selectedItem.getOrderId()) {
//                    que = new OrderDetailDTO(orderDetailDTO.getOrderDetailId(),orderDetailDTO.getOrderdQty(),orderDetailDTO.getPrice(),orderDetailDTO.getOrderDTO(),orderDetailDTO.getMealDTO());
//                    System.out.println(que.getOrderDetailId());
                    try {
                        ArrayList<MealChefDTO> mealDTOArrayList = new ArrayList<>();
                        ArrayList<MealDTO> allMeal = (ArrayList<MealDTO>) mealService.getAllMeal();
                        for (MealDTO mealDTO : allMeal) {
                            if (mealDTO.getMealId() == orderDetailDTO.getMealDTO().getMealId()) {
                                MealChefDTO mealDTO1 = new MealChefDTO();
                                mealDTO1.setMealId(mealDTO.getMealId());
                                mealDTO1.setMealName(mealDTO.getMealName());
                                mealDTO1.setMealCategory(mealDTO.getMealCategory());
                                mealDTO1.setQty(orderDetailDTO.getOrderdQty());
                                mealDTOArrayList.add(mealDTO1);

                                mealTable.setItems(FXCollections.observableArrayList(mealDTOArrayList));
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            takeOrderButton.setDisable(false);
        }

    }

    @FXML
    void takeOrderButtonAction(ActionEvent event) {
        try {
            lastChefQueue = chefQueueService.getLastChefQueue();
            startDate = new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startTimeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        takeOrderButton.setDisable(true);
        cancelOrderbutton.setDisable(false);
        finishedOrderButton.setDisable(false);
        orderTable.setDisable(true);
    }
}
