package lk.ijse.dinemore.controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.dinemore.common.JasperReportGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminReportController {
    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    public void initialize(){
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
    }

    @FXML
    void fullReportCookingButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Cooking/All_Cooking.jasper");
    }

    @FXML
    void fullReportOfBestCustomer(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Customer/Best_Customer.jasper");
    }

    @FXML
    void fullReportOfBestMeal(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Meal/Best_Product.jasper");
    }

    @FXML
    void fullReportOfCustomer(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Customer/Customer.jasper");
    }

    @FXML
    void fullReportOfDeliveryButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Delivery/All_Delivery.jasper");
    }

    @FXML
    void fullReportOfMeals(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Meal/All_Meal.jasper");
    }

    @FXML
    void fullReportOfOperatorsButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Operator/All_Operators.jasper");
    }

    @FXML
    void fullReportOrdersButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Orders/All_Orders.jasper");
    }

    @FXML
    void fullReportsOfBestChefButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Chef/Best_Chef.jasper");
    }

    @FXML
    void fullReportsOfBestDeliveryBoyButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Delivery_boy/Best_DeliveryBoy.jasper");
    }

    @FXML
    void fullReportsOfChefButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Chef/All_Chef.jasper");
    }

    @FXML
    void fullReportsOfDeliveryBoyButton(ActionEvent event) {
        JasperReportGenerator.getJasperReport(this.getClass(),"/lk/ijse/dinemore/common/reports/Delivery_boy/All_Delivery_Boy.jasper");
    }

}
