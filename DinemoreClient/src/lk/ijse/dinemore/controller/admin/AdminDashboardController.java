package lk.ijse.dinemore.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dinemore.common.LoadPane;

public class AdminDashboardController {

    @FXML
    private AnchorPane loadPane;

    @FXML
    void chefButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/admin/admin-chef.fxml",loadPane);
    }

    @FXML
    void deliveryButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/admin/admin-delivery.fxml",loadPane);
    }

    @FXML
    void operatorButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/admin/admin-toperators.fxml",loadPane);
    }

    @FXML
    void mealButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/admin/admin-meal.fxml",loadPane);
    }

    @FXML
    void viewOrderButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/admin/admin-vieworder.fxml",loadPane);
    }

    @FXML
    void reportButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/admin/admin-reports.fxml",loadPane);
    }

    public void initialize(){
        operatorButtonAction(new ActionEvent());
    }
}
