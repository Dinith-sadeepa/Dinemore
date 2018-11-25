package lk.ijse.dinemore.controller.delivery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dinemore.common.LoadPane;

public class DeliveryDashboardController {

    @FXML
    private AnchorPane loadPane;

    public void initialize(){
        takeOrderButtonAction(new ActionEvent());
    }

    @FXML
    void takeOrderButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/delivery/delivery-takeorder .fxml",loadPane);
    }

    @FXML
    void viewOrderButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/delivery/delivery-vieworder.fxml",loadPane);
    }
}
