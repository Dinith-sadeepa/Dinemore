package lk.ijse.dinemore.controller.chef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dinemore.common.LoadPane;

public class ChefDashboardController {

    @FXML
    private AnchorPane loadPane;

    public void initialize(){
        takeOrderButtonAction(new ActionEvent());
    }

    @FXML
    void takeOrderButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/chef/chef-takeorder.fxml",loadPane);
    }

    @FXML
    void viewOrderButtonAction(ActionEvent event) {
        LoadPane.loadPane(this.getClass(),"/lk/ijse/dinemore/view/chef/chef-vieworder.fxml",loadPane);
    }
}
