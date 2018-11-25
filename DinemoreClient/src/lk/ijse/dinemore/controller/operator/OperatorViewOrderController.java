package lk.ijse.dinemore.controller.operator;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.dinemore.common.orders.ViewOrdersClass;
import lk.ijse.dinemore.dto.*;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperatorViewOrderController {
    @FXML
    private TextField searchOrderText;

    @FXML
    private TableView<ViewOrderDTO> orderTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    private CustomerService customerService;
    private OrderService orderService;
    private CookingService cookingService;
    private OrderDetailService orderDetailService;
    private DeliveryService deliveryService;

    public void initialize() {
        try {
            cookingService = (CookingService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.COOKING);
            customerService = (CustomerService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CUSTOMER);
            orderDetailService = (OrderDetailService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
            orderService = (OrderService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.ORDERS);
            deliveryService = (DeliveryService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDateTime();
        ViewOrdersClass.loadColumns(orderTable);
        ViewOrdersClass.loadOrders(orderTable,customerService,orderService,orderDetailService,cookingService,deliveryService);
    }

    private void setDateTime() {
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
    }

    @FXML
    void searchOrderKeyReleased(KeyEvent event) {
        ObservableList<ViewOrderDTO> getarray = ViewOrdersClass.getarray();
        searchOrderText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchOrderText.textProperty().get().isEmpty()) {
                    orderTable.setItems(getarray);
                    return;
                }
                ObservableList<ViewOrderDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<ViewOrderDTO, ?>> cols = orderTable.getColumns();
                for (int i = 0; i < getarray.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        System.out.println("");
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(getarray.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchOrderText.textProperty().get().toLowerCase())) {
                            tableItem.add(getarray.get(i));
                            break;
                        }
                    }
                }
                orderTable.setItems(tableItem);
            }
        });
    }


    @FXML
    void placeOrdersButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dinemore/view/operator/telephone_operator-placeorder.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
