package lk.ijse.dinemore.controller.operator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dinemore.common.LoadPane;

public class OperatorDashboardController {

    @FXML
    private AnchorPane loadPane;

    public void initialize(){
        placeOrderButtonAction(new ActionEvent());
    }

    @FXML
    void placeOrderButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/operator/telephone_operator-placeorder.fxml",loadPane);
    }

    @FXML
    void viewOrderButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/operator/telephone_operator-vieworder.fxml",loadPane);
    }
}
