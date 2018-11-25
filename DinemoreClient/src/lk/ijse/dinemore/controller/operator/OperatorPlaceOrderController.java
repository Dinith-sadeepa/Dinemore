package lk.ijse.dinemore.controller.operator;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.dinemore.common.validation.Validation;
import lk.ijse.dinemore.controller.admin.AdminChefController;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.*;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OperatorPlaceOrderController {
    @FXML
    private JFXTextField mealNameText;

    @FXML
    private JFXTextField mealUnitPriceText;

    @FXML
    private JFXTextField mealQtyOnHandText;

    @FXML
    private JFXTextField mealQty;

    @FXML
    private JFXTextField mealCategoryText;

    @FXML
    private TableView<NewOrderDTO> mealTable;

    @FXML
    private TableColumn<MealDTO, Integer> mealIdColumn;

    @FXML
    private TableColumn<MealDTO, String> mealNameColumn;

    @FXML
    private TableColumn<MealDTO, String> mealCategoryColumn;

    @FXML
    private TableColumn<MealDTO, Double> mealUnitPriceColumn;

    @FXML
    private TableColumn<MealDTO, Integer> mealQtyColumn;

    @FXML
    private TableColumn<NewOrderDTO, Double> mealAmountColumn;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private JFXTextField customerNameText;

    @FXML
    private JFXTextField customerAddressText;

    @FXML
    private JFXTextField customerTPText;

    @FXML
    private JFXTextField creditCardInformationText;

    @FXML
    private JFXTextField mmText;

    @FXML
    private JFXTextField yyText;

    @FXML
    private JFXRadioButton visaRButton;

    @FXML
    private ToggleGroup credit;

    @FXML
    private JFXRadioButton masterRButton;

    private MealService mealService;
    private CustomerService customerService;
    private OrderService orderService;
    private LogInService logInService;
    private OperatorService operatorService;
    private ObservableList<NewOrderDTO> dtos = FXCollections.observableArrayList();

    public void initialize() {
        setDateTime();
        try {
            mealService = (MealService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.MEAL);
            customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDERS);
            logInService = (LogInService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.LOG_IN);
            operatorService = (OperatorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.OPERATOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadMealName();
        loadMealTableColumn();
        loadCustomerNames();
    }

    private void loadCustomerNames() {
        try {
            ArrayList<CustomerDTO> customerDTOS = (ArrayList<CustomerDTO>) customerService.getAllCustomer();
            if (customerDTOS != null) {
                ArrayList<String> customerNames = new ArrayList<>();
                for (CustomerDTO customerDTO : customerDTOS) {
                    customerNames.add(customerDTO.getCustomerName());
                }

                TextFields.bindAutoCompletion(customerNameText, customerNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMealTableColumn() {
        mealTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mealId"));
        mealTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("mealName"));
        mealTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mealCategory"));
        mealTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mealUnitPrice"));
        mealTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("mealQty"));
        mealTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void setDateTime() {
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
    }

    private void loadMealName() {
        try {
            ArrayList<MealDTO> mealDTOS = (ArrayList<MealDTO>) mealService.getAllMeal();

            ArrayList<String> mealNames = new ArrayList<>();
            for (MealDTO meal : mealDTOS) {
                mealNames.add(meal.getMealName());
            }

            TextFields.bindAutoCompletion(mealNameText, mealNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelOrderButtonAction(ActionEvent event) {
        mealTable.getItems().clear();
        mealQty.clear();
        mealNameText.clear();
        mealCategoryText.clear();
        mealUnitPriceText.clear();
        customerNameText.clear();
        customerTPText.clear();
        customerAddressText.clear();
        creditCardInformationText.clear();
        mmText.clear();
        yyText.clear();
        totalAmountLabel.setText("");
        initialize();
    }

    @FXML
    void creditCardInformationTextAction(ActionEvent event) {
        mmText.requestFocus();
    }

    @FXML
    void customerAddressTextAction(ActionEvent event) {
//        creditCardInformationText.requestFocus();
        placeOrderButtonAction(event);
    }

    @FXML
    void customerNameTextAction(ActionEvent event) {
        if(Validation.nameValidate(customerNameText.getText())) {
            ArrayList<CustomerDTO> customerDTOS = null;
            try {
                customerDTOS = (ArrayList<CustomerDTO>) customerService.getAllCustomer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (CustomerDTO customerDTO : customerDTOS) {
                if (customerDTO.getCustomerName().equals(customerNameText.getText())) {
                    customerTPText.setText(customerDTO.getTelephone_no());
                    customerAddressText.setText(customerDTO.getCustomerAddress());
                }
            }
            customerTPText.requestFocus();
        }else{
            customerNameText.selectAll();
        }
    }

    @FXML
    void customerTPTextAction(ActionEvent event) {
        if(Validation.telephoneNoValidate(customerTPText.getText())) {
            customerAddressText.requestFocus();
        }else{
            customerTPText.selectAll();
        }
    }

    @FXML
    void mealNameTextAction(ActionEvent event) {
            try {
                ArrayList<MealDTO> mealDTOS = (ArrayList<MealDTO>) mealService.getAllMeal();
                for (MealDTO mealDTO : mealDTOS) {
                    if (mealDTO.getMealName().equals(mealNameText.getText())) {
                        mealCategoryText.setText(mealDTO.getMealCategory());
                        mealUnitPriceText.setText(mealDTO.getMealUnitPrice() + "");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mealQty.requestFocus();
    }

    @FXML
    void mealQtyAction(ActionEvent event) throws Exception {
        if(Validation.integerValueValidate(mealQty.getText())) {
            ArrayList<MealDTO> mealDTOS = (ArrayList<MealDTO>) mealService.getAllMeal();
            int id = 0;
            for (MealDTO mealDTO : mealDTOS) {
                if (mealDTO.getMealName().equals(mealNameText.getText())) {
                    id = mealDTO.getMealId();
                }
            }
            NewOrderDTO newOrderDTO = new NewOrderDTO(id, mealNameText.getText(), mealCategoryText.getText(), Double.parseDouble(mealUnitPriceText.getText()), Integer.parseInt(mealQty.getText()), Double.parseDouble(mealUnitPriceText.getText()) * Integer.parseInt(mealQty.getText()));

            dtos.add(newOrderDTO);
            mealTable.setItems(dtos);

            ArrayList<Double> amount = new ArrayList<>();
            for (NewOrderDTO newOrderDTO1 : mealTable.getItems()) {
                amount.add(mealAmountColumn.getCellObservableValue(newOrderDTO1).getValue());
            }
            double total = 0;
            for (double v : amount) {
                total += v;
            }
            totalAmountLabel.setText(total + "");
            customerNameText.requestFocus();
        }else{
            mealQty.selectAll();
        }
    }

    @FXML
    void mmTextAction(ActionEvent event) {
        yyText.requestFocus();
    }

    @FXML
    void placeOrderButtonAction(ActionEvent event) {
        boolean isAddedCustomer = false;

        //customer object

        CustomerDTO customerDTO = new CustomerDTO(customerNameText.getText(), customerAddressText.getText(), customerTPText.getText());
        try {
            ArrayList<CustomerDTO> allCustomer = (ArrayList<CustomerDTO>) customerService.getAllCustomer();
            for (CustomerDTO customerDTO1 :allCustomer) {
                if(customerDTO1.getCustomerName().equals(customerNameText.getText()) && customerDTO1.getCustomerAddress().equals(customerAddressText.getText()) && customerDTO1.getTelephone_no().equals(customerTPText.getText())){
                    customerDTO = new CustomerDTO(customerDTO1.getCustomerId(),customerDTO1.getCustomerName(),customerDTO1.getCustomerAddress(),customerDTO.getTelephone_no());
                }
            }

            //userid
            ArrayList<LogInDTO> allLogin = (ArrayList<LogInDTO>) logInService.getAllLogin();
            int operatorId = 0;
            for (LogInDTO logInDTO : allLogin) {
                operatorId = logInDTO.getUserId();
            }
            ArrayList<OperatorDTO> allOperator = (ArrayList<OperatorDTO>) operatorService.getAllOperator();
            OperatorDTO operatorDTO = null;
            for (OperatorDTO operator : allOperator) {
                if (operator.getOperatorId() == operatorId) {
                    operatorDTO = new OperatorDTO(operatorId, operator.getOperatorName(), operator.getOperatorNIC(), operator.getOperatorAddress(), operator.getOperatorUserName(), operator.getOperatorPassword());
                }
            }

            //orders object
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderDate(new Date());
            orderDTO.setCustomerDTO(customerDTO);
            orderDTO.setOperatorDTO(operatorDTO);

            //orderdetail list
            List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
            MealDTO mealDTO = new MealDTO();
            ArrayList<MealDTO> mealDTOS = (ArrayList<MealDTO>) mealService.getAllMeal();
            for (MealDTO meal : mealDTOS) {
                if (meal.getMealName().equals(mealNameText.getText())) {
                    mealDTO.setMealId(meal.getMealId());
                    mealDTO.setMealName(meal.getMealName());
                    mealDTO.setMealCategory(meal.getMealCategory());
                    mealDTO.setMealUnitPrice(meal.getMealUnitPrice());
                }
            }

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setMealDTO(mealDTO);
            orderDetailDTO.setOrderDTO(orderDTO);
            orderDetailDTO.setOrderdQty(Integer.parseInt(mealQty.getText()));
            orderDetailDTO.setPrice(Integer.parseInt(mealQty.getText()) * mealDTO.getMealUnitPrice());

            orderDetailDTOS.add(orderDetailDTO);
            orderDTO.setOrderDetailDTOS(orderDetailDTOS);


            if (!checkCustomer()) {
//                isAddedCustomer = customerService.addCustomer(customerDTO);
                isAddedCustomer = true;
            } else {
                isAddedCustomer = true;
            }

            if (isAddedCustomer) {
                boolean isAdded = orderService.addOrder(orderDTO);
                if(isAdded){
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Order Placed!", ButtonType.OK);
                    a.setHeaderText(null);
                    a.showAndWait();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkCustomer() {
        try {
            ArrayList<CustomerDTO> customerDTOS = (ArrayList<CustomerDTO>) customerService.getAllCustomer();
            for (CustomerDTO customerDTO : customerDTOS) {
                if (customerDTO.getCustomerName().equals(customerNameText.getText()) && customerDTO.getCustomerAddress().equals(customerAddressText.getText()) && customerDTO.getTelephone_no().equals(customerTPText.getText())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @FXML
    void yyTextAction(ActionEvent event) {
        visaRButton.requestFocus();
    }

    @FXML
    void viewOrdersButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinemore/view/operator/telephone_operator-vieworder.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
