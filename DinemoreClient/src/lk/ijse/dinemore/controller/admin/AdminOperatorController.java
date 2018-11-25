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
import lk.ijse.dinemore.dto.OperatorDTO;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.OperatorService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminOperatorController {
    @FXML
    private TextField searchOperatorText;

    @FXML
    private TableView<OperatorDTO> operatorTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private JFXTextField operatorNameText;

    @FXML
    private JFXTextField operatorAddressText;

    @FXML
    private JFXTextField operatorNICText;

    @FXML
    private JFXTextField operatorUsernameText;

    @FXML
    private JFXTextField operatorPasswordText;

    @FXML
    private JFXButton addOperatorButton;

    @FXML
    private JFXButton deleteOperatorButton;

    @FXML
    private JFXButton updateOperatorButton;

    private OperatorService operatorService;
    private ObservableList<OperatorDTO> operatorDTOS;
    private int operatorId;

    public void initialize(){
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));

        try {
            operatorService = (OperatorService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.OPERATOR);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadColumns();
        loadOperators();
    }

    private void loadOperators() {
        try {
            ArrayList<OperatorDTO> allOperator = (ArrayList<OperatorDTO>) operatorService.getAllOperator();
            operatorDTOS = FXCollections.observableArrayList(allOperator);
            operatorTable.setItems(operatorDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadColumns() {
        operatorTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("operatorId"));
        operatorTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("operatorName"));
        operatorTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("operatorNIC"));
        operatorTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("operatorAddress"));
        operatorTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("operatorUserName"));
        operatorTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("operatorPassword"));
    }

    @FXML
    void addOperatorAction(ActionEvent event) throws Exception {
        boolean isAdded = operatorService.addOperator(new OperatorDTO(
                operatorNameText.getText(),
                operatorNICText.getText(),
                operatorAddressText.getText(),
                "ope : "+operatorUsernameText.getText(),
                operatorPasswordText.getText()
        ));

        if(isAdded){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Operator Added!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadOperators();
            clearFields();
            operatorNameText.requestFocus();
        }
    }

    @FXML
    void deleteOperatorAction(ActionEvent event) throws Exception {
        boolean b = operatorService.deleteOperator(operatorId);
        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Operator Deleted!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadOperators();
            clearFields();
        }
    }

    @FXML
    void operatorAddressTextAction(ActionEvent event) {
        operatorUsernameText.requestFocus();
    }

    @FXML
    void operatorNICTextAction(ActionEvent event) {
        if(Validation.nicValidate(operatorNICText.getText())) {
            operatorAddressText.requestFocus();
        }else{
            operatorNICText.selectAll();
        }
    }

    @FXML
    void operatorNameTextAction(ActionEvent event) {
        if(Validation.nameValidate(operatorNameText.getText())) {
            operatorNICText.requestFocus();
        }else{
            operatorNameText.selectAll();
        }
    }

    @FXML
    void operatorPasswordTextAction(ActionEvent event) {
        try {
            addOperatorAction(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void operatorTableClicked(MouseEvent event) {
        OperatorDTO selectedItem = operatorTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            operatorId = selectedItem.getOperatorId();
            addOperatorButton.setDisable(true);
            deleteOperatorButton.setDisable(false);
            updateOperatorButton.setDisable(false);

            operatorNameText.setText(selectedItem.getOperatorName());
            operatorAddressText.setText(selectedItem.getOperatorAddress());
            operatorNICText.setText(selectedItem.getOperatorNIC());
            operatorUsernameText.setText(selectedItem.getOperatorUserName());
            operatorPasswordText.setText(selectedItem.getOperatorPassword());
        }
    }

    @FXML
    void operatorUsernameTextAction(ActionEvent event) {
        operatorPasswordText.requestFocus();
    }

    @FXML
    void searchOperatorTextKeyReleased(KeyEvent event) {
        searchOperatorText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchOperatorText.textProperty().get().isEmpty()) {
                    operatorTable.setItems(operatorDTOS);
                    return;
                }
                ObservableList<OperatorDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<OperatorDTO, ?>> cols = operatorTable.getColumns();
                for (int i = 0; i < operatorDTOS.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(operatorDTOS.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        System.out.println();
                        if (cellValue.contains(searchOperatorText.textProperty().get().toLowerCase())) {
                            tableItem.add(operatorDTOS.get(i));
                            System.out.println();
                            break;
                        }
                    }
                }
                operatorTable.setItems(tableItem);
            }
        });
    }

    @FXML
    void updateOperatorAction(ActionEvent event) throws Exception {
        boolean b = operatorService.updateOperator(new OperatorDTO(
                operatorId,
                operatorNameText.getText(),
                operatorNICText.getText(),
                operatorAddressText.getText(),
                "ope : " + operatorUsernameText.getText(),
                operatorPasswordText.getText()
        ));
        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Operator Updated!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadOperators();
            clearFields();
        }
    }

    private void clearFields() {
        operatorPasswordText.clear();
        addOperatorButton.setDisable(false);
        deleteOperatorButton.setDisable(true);
        operatorUsernameText.clear();
        operatorNICText.clear();
        updateOperatorButton.setDisable(true);
        operatorNameText.clear();
        operatorAddressText.clear();
    }

}
