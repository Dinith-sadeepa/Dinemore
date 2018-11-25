package lk.ijse.dinemore.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.dinemore.common.validation.Validation;
import lk.ijse.dinemore.dto.DeliveryBoyDTO;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.DeliveryBoyService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDeliveryController {


    @FXML
    private TextField searchDeliveryText;

    @FXML
    private TableView<DeliveryBoyDTO> deliveryTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private JFXTextField deliveryNameText;

    @FXML
    private JFXTextField deliveryAddressText;

    @FXML
    private JFXTextField deliveryNicText;

    @FXML
    private JFXTextField deliveryUserNameText;

    @FXML
    private JFXTextField deliveryPasswordText;

    @FXML
    private JFXButton addDeliveryButton;

    @FXML
    private JFXButton deleteDeliveryButton;

    @FXML
    private JFXButton updateDeliveryButton;

    private DeliveryBoyService deliveryBoyService;
    private ObservableList<DeliveryBoyDTO> deliveryBoyDTOS;
    private int deliveryBoyId;

    public void initialize(){
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));

        try {
            deliveryBoyService = (DeliveryBoyService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.DELIVERY_BOY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadColumns();
        loadDeliveryBoys();
    }

    private void loadDeliveryBoys() {
        try {
            ArrayList<DeliveryBoyDTO> allDeliveryBoy = (ArrayList<DeliveryBoyDTO>)deliveryBoyService.getAllDeliveryBoy();
            deliveryBoyDTOS = FXCollections.observableArrayList(allDeliveryBoy);
            deliveryTable.setItems(deliveryBoyDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadColumns() {
        deliveryTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("deliveryBoyId"));
        deliveryTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("deliveryBoyName"));
        deliveryTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("deliveryBoyNIC"));
        deliveryTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("deliveryBoyAddress"));
        deliveryTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("deliveryBoyUserName"));
        deliveryTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("deliveryBoyPassword"));
    }

    @FXML
    void addDeliveryButtonAction(ActionEvent event) {
        try {
            boolean isAdded = deliveryBoyService.addDeliveryBoy(new DeliveryBoyDTO(
                    deliveryNameText.getText(),
                    deliveryNicText.getText(),
                    deliveryAddressText.getText(),
                    "del : "+deliveryUserNameText.getText(),
                    deliveryPasswordText.getText()

            ));
            if(isAdded){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Delivery Boy Added!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                loadDeliveryBoys();
                clearFields();
                deliveryNameText.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteDeliveryButtonAction(ActionEvent event) throws Exception {
        boolean b = deliveryBoyService.deleteDeliveryBoyr(deliveryBoyId);
        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Delivery Boy Deleted!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadDeliveryBoys();
            clearFields();
        }
    }
    private void clearFields() {
        deliveryPasswordText.clear();
        addDeliveryButton.setDisable(false);
        updateDeliveryButton.setDisable(true);
        deleteDeliveryButton.setDisable(true);
        deliveryNameText.clear();
        deliveryNicText.clear();
        deliveryAddressText.clear();
        deliveryUserNameText.clear();
    }

    @FXML
    void deliveryAddressTextAction(ActionEvent event) {
        deliveryUserNameText.requestFocus();
    }

    @FXML
    void deliveryNameTextAction(ActionEvent event) {
        if(Validation.nameValidate(deliveryNameText.getText())) {
            deliveryNicText.requestFocus();
        }else{
            deliveryNameText.selectAll();
        }
    }

    @FXML
    void deliveryNicTextAction(ActionEvent event) {
        if(Validation.nicValidate(deliveryNicText.getText())) {
            deliveryAddressText.requestFocus();
        }else{
            deliveryNicText.selectAll();
        }
    }

    @FXML
    void deliveryPasswordTextAction(ActionEvent event) {
        addDeliveryButtonAction(event);
    }

    @FXML
    void deliveryTableClicked(MouseEvent event) {
        DeliveryBoyDTO selectedItem = deliveryTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            addDeliveryButton.setDisable(true);
            updateDeliveryButton.setDisable(false);
            deleteDeliveryButton.setDisable(false);

            deliveryBoyId = selectedItem.getDeliveryBoyId();
            deliveryNameText.setText(selectedItem.getDeliveryBoyName());
            deliveryAddressText.setText(selectedItem.getDeliveryBoyAddress());
            deliveryNicText.setText(selectedItem.getDeliveryBoyNIC());
            deliveryUserNameText.setText(selectedItem.getDeliveryBoyUserName());
            deliveryPasswordText.setText(selectedItem.getDeliveryBoyPassword());
        }
    }

    @FXML
    void deliveryUserNameTextAction(ActionEvent event) {
        deliveryPasswordText.requestFocus();
    }

    @FXML
    void searchTextKeyReleased(KeyEvent event) {
        searchDeliveryText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchDeliveryText.textProperty().get().isEmpty()) {
                    deliveryTable.setItems(deliveryBoyDTOS);
                    return;
                }
                ObservableList<DeliveryBoyDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<DeliveryBoyDTO, ?>> cols = deliveryTable.getColumns();

                for (int i = 0; i < deliveryBoyDTOS.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        System.out.println();
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(deliveryBoyDTOS.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchDeliveryText.textProperty().get().toLowerCase())) {
                            tableItem.add(deliveryBoyDTOS.get(i));
                            break;
                        }
                    }
                }
                deliveryTable.setItems(tableItem);
            }
        });
    }

    @FXML
    void updateDeliveryButtonAction(ActionEvent event) throws Exception {
        boolean b = deliveryBoyService.updateDeliveryBoy(new DeliveryBoyDTO(
                deliveryBoyId,
                deliveryNameText.getText(),
                deliveryNicText.getText(),
                deliveryAddressText.getText(),
                "del : " + deliveryUserNameText.getText(),
                deliveryPasswordText.getText()

        ));
        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Delivery Boy Updated!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadDeliveryBoys();
            clearFields();
        }
    }

}
