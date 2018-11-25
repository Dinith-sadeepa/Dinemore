package lk.ijse.dinemore.controller.delivery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryViewOrderController {
    @FXML
    private TextField searchOrderText;

    @FXML
    private TableView<?> orderTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    public void initialize(){
        setDateTime();
    }

    private void setDateTime() {
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
    }

    @FXML
    void searchOrderTextAction(ActionEvent event) {

    }
}
