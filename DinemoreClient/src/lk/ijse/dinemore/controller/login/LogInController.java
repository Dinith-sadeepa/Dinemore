package lk.ijse.dinemore.controller.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dinemore.dto.ChefDTO;
import lk.ijse.dinemore.dto.DeliveryBoyDTO;
import lk.ijse.dinemore.dto.LogInDTO;
import lk.ijse.dinemore.dto.OperatorDTO;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.ChefService;
import lk.ijse.dinemore.service.custom.DeliveryBoyService;
import lk.ijse.dinemore.service.custom.LogInService;
import lk.ijse.dinemore.service.custom.OperatorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogInController {

    @FXML
    private TextField userNameText;

    @FXML
    private PasswordField passwordText;

    private OperatorService operatorService;
    private ChefService chefService;
    private DeliveryBoyService deliveryBoyService;
    private LogInService logInService;
    private int userId;

    public void initialize(){
        try {
            operatorService = (OperatorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.OPERATOR);
            chefService = (ChefService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEF);
            deliveryBoyService = (DeliveryBoyService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVERY_BOY);
            logInService = (LogInService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.LOG_IN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void passwordTextAction(ActionEvent event) throws Exception {
        String userName = userNameText.getText();
        String password = passwordText.getText();

        Parent root = null;

        if(userName.equals("admin") && password.equals("admin")){
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinemore/view/admin/admin-dashboard.fxml"));
        }else if(checkIsOperator(userName,password)){
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinemore/view/operator/telephone_operator-placeorder.fxml"));
        }else if(checkIsChef(userName,password)){
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinemore/view/chef/chef-takeorder.fxml"));
        }else if(checkIsDeliveryBoy(userName,password)){
            root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinemore/view/delivery/delivery-takeorder .fxml"));
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR, "UserName Can't find!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            userNameText.setText(null);
            passwordText.setText(null);
            userNameText.requestFocus();
        }
        if(root != null) {
            logInService.addLogIn(new LogInDTO(userId,new Date()));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        }
    }

    private boolean checkIsDeliveryBoy(String userName, String password) {
        try {
            ArrayList<DeliveryBoyDTO> deliveryBoyDTOS = (ArrayList<DeliveryBoyDTO>) deliveryBoyService.getAllDeliveryBoy();

            for (DeliveryBoyDTO deliveryBoyDTO:deliveryBoyDTOS) {
                if(deliveryBoyDTO.getDeliveryBoyUserName().equals("del : "+userName) && deliveryBoyDTO.getDeliveryBoyPassword().equals(password)){
                    userId = deliveryBoyDTO.getDeliveryBoyId();
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkIsChef(String userName, String password) {
        try {
            ArrayList<ChefDTO> chefDTOS = (ArrayList<ChefDTO>) chefService.getAllChef();

            for (ChefDTO chefDTO:chefDTOS) {
                if(chefDTO.getChefUserName().equals("chef : "+userName) && chefDTO.getChefPassword().equals(password)){
                    userId = chefDTO.getChefId();
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkIsOperator(String username , String password) {
        try {
            ArrayList<OperatorDTO> allOperator = (ArrayList<OperatorDTO>) operatorService.getAllOperator();

            for (OperatorDTO operatorDTO:allOperator) {
                if(operatorDTO.getOperatorUserName().equals("ope : "+username) && operatorDTO.getOperatorPassword().equals(password)){
                    userId = operatorDTO.getOperatorId();
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    void userNameTextAction(ActionEvent event) {
        passwordText.requestFocus();
    }



}
