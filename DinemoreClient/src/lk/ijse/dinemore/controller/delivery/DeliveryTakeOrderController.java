package lk.ijse.dinemore.controller.delivery;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.*;

import java.text.SimpleDateFormat;
import java.util.*;


public class DeliveryTakeOrderController {

    @FXML
    private TableView<OrderDTO> orderTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private TableView<CustomerDTO> customerTable;

    @FXML
    private JFXButton takeOrderButton;

    @FXML
    private JFXButton cancelOrderButton;

    @FXML
    private JFXButton finishOrderButton;

    @FXML
    private Label startTimeLabel;

    @FXML
    private Label endTimeLabel;

    private DeliveryQueueService deliveryQueueService;
    private OrderService orderService;
    private CustomerService customerService;
    private DeliveryService deliveryService;
    private LogInService logInService;
    private DeliveryBoyService deliveryBoyService;
    private OrderDetailService orderDetailService;

    private ArrayList<OrderDetailDTO> orderDetails = new ArrayList<>();
    private OrderDetailDTO lastDeliveryQueue = new OrderDetailDTO();
    private Date startDate;
    private int deliveryBoyId;
    private int orderId;
    private LinkedList<OrderDetailDTO> deliveryQueue;

    public void initialize() {

        try {
            logInService = (LogInService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.LOG_IN);
            deliveryQueueService = (DeliveryQueueService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVERY_QUEUE);
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDERS);
            orderDetailService = (OrderDetailService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
            customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
            deliveryService = (DeliveryService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVERY);
            deliveryBoyService = (DeliveryBoyService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVERY_BOY);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setDateTime();
        loadColumns();
        loadCustomerTableColumn();
        new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    loadOrders();
                }
            }, new Date(), 2000);
        }).start();
        getDeliveryBoyId();
    }

    private void getDeliveryBoyId() {
        try {
            ArrayList<LogInDTO> allLogin = (ArrayList<LogInDTO>) logInService.getAllLogin();
            for (LogInDTO l : allLogin) {
                System.out.println();
                deliveryBoyId = l.getUserId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadOrders() {
        orderTable.getItems().clear();
        try {
            deliveryQueue = (LinkedList<OrderDetailDTO>) deliveryQueueService.getAllDeliveryQueue();

            if (deliveryQueue != null) {
                ArrayList<OrderDTO> allOrders = (ArrayList<OrderDTO>) orderService.getAllOrder();
                orderDetails.clear();
                for (int i = deliveryQueue.size() - 1; i >= 0; i--) {
                    orderDetails.add(deliveryQueue.get(i));
                }

                ArrayList<OrderDTO> orderDTOArrayList = new ArrayList<>();
                for (OrderDetailDTO orderDetailDTO : orderDetails) {
                    for (OrderDTO orderDTO1 : allOrders) {
                        if (orderDetailDTO.getOrderDTO().getOrderId() == orderDTO1.getOrderId()) {
                            System.out.println();
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

    private void loadCustomerTableColumn() {
        customerTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("telephone_no"));
    }

    private void loadColumns() {
        orderTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }

    private void setDateTime() {
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
    }

    @FXML
    void cancelOrderButtonAction(ActionEvent event) {
        try {
            deliveryQueueService.addLastDeliveryQueue(lastDeliveryQueue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        customerTable.getItems().clear();
        takeOrderButton.setDisable(true);
        cancelOrderButton.setDisable(true);
        finishOrderButton.setDisable(true);
        orderTable.setDisable(false);
    }

    @FXML
    void finishedOrderButtonAction(ActionEvent event) {
        try {
            ArrayList<DeliveryBoyDTO> deliveryBoyDTOS = (ArrayList<DeliveryBoyDTO>) deliveryBoyService.getAllDeliveryBoy();
            DeliveryBoyDTO deliveryBoyDTO = new DeliveryBoyDTO();
            for (DeliveryBoyDTO delivery : deliveryBoyDTOS) {
                if (delivery.getDeliveryBoyId() == deliveryBoyId) {
                    deliveryBoyDTO = new DeliveryBoyDTO(deliveryBoyId,delivery.getDeliveryBoyName(),delivery.getDeliveryBoyNIC(),delivery.getDeliveryBoyAddress(),delivery.getDeliveryBoyUserName(),delivery.getDeliveryBoyPassword());
                }
            }
            ArrayList<OrderDetailDTO> allOrderDetail = (ArrayList<OrderDetailDTO>) orderDetailService.getAllOrderDetail();
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            for (OrderDetailDTO orderDetailDTO1 : allOrderDetail) {
                if (orderDetailDTO1.getOrderDTO().getOrderId() == orderId) {
                    orderDetailDTO = new OrderDetailDTO(orderDetailDTO1.getOrderDetailId(), orderDetailDTO1.getOrderdQty(), orderDetailDTO1.getPrice(), orderDetailDTO1.getOrderDTO(), orderDetailDTO1.getMealDTO());
                }
            }

            boolean b = deliveryService.addDelivery(new DeliveryDTO(startDate, new Date(),orderDetailDTO,deliveryBoyDTO));
            if (b) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Deliver Done!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                disableControl();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disableControl() {
        cancelOrderButton.setDisable(true);
        finishOrderButton.setDisable(true);
        customerTable.getItems().clear();
        orderTable.setDisable(false);
    }

    @FXML
    void orderTableClicked(MouseEvent event) {
        int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != 0) {
            Alert a = new Alert(Alert.AlertType.ERROR, "You should select first!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
        } else {
            OrderDTO selectedItem = orderTable.getSelectionModel().getSelectedItem();
            for (OrderDetailDTO orderDetailDTO : orderDetails) {
                if (orderDetailDTO.getOrderDTO().getOrderId() == selectedItem.getOrderId()) {
                    try {
                        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
                        ArrayList<CustomerDTO> allCustomer = (ArrayList<CustomerDTO>) customerService.getAllCustomer();
                        for (CustomerDTO customerDTO : allCustomer) {
                            if (customerDTO.getCustomerId() == orderDetailDTO.getOrderDTO().getCustomerDTO().getCustomerId()) {
                                CustomerDTO customerDTO1 = new CustomerDTO();
                                customerDTO1.setCustomerId(customerDTO.getCustomerId());
                                customerDTO1.setCustomerName(customerDTO.getCustomerName());
                                customerDTO1.setCustomerAddress(customerDTO.getCustomerAddress());
                                customerDTO1.setTelephone_no(customerDTO.getTelephone_no());
                                customerDTOS.add(customerDTO1);

                                customerTable.setItems(FXCollections.observableArrayList(customerDTOS));
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
            lastDeliveryQueue=deliveryQueueService.getLastDeliveryQueue();
            startDate = new Date();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startTimeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        takeOrderButton.setDisable(true);
        cancelOrderButton.setDisable(false);
        finishOrderButton.setDisable(false);
        orderTable.setDisable(true);
    }
}
