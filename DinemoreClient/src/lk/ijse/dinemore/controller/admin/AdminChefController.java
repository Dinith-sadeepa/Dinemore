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
import lk.ijse.dinemore.dto.ChefDTO;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.ChefService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminChefController {
    @FXML
    private TextField searchChefText;

    @FXML
    private TableView<ChefDTO> chefTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private JFXTextField chefNameText;

    @FXML
    private JFXTextField chefAddressText;

    @FXML
    private JFXTextField chefNICText;

    @FXML
    private JFXTextField chefUserNameText;

    @FXML
    private JFXTextField chefPasswordText;

    @FXML
    private JFXButton addChefButton;

    @FXML
    private JFXButton deleteChefButton;

    @FXML
    private JFXButton updateChefButton;

    private ChefService chefService;
    private ObservableList<ChefDTO> chefDTOS;
    private int chefId;

    public void initialize(){
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));

        try {
            chefService = (ChefService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.CHEF);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadColumns();
        loadChefs();
    }

    private void loadChefs() {
        try {
            ArrayList<ChefDTO> allChef =(ArrayList<ChefDTO>) chefService.getAllChef();
            chefDTOS = FXCollections.observableArrayList(allChef);
            chefTable.setItems(chefDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadColumns() {
        chefTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("chefId"));
        chefTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("chefName"));
        chefTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("chefNIC"));
        chefTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("chefAddress"));
        chefTable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("chefUserName"));
        chefTable.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("chefPassword"));
    }

    @FXML
    void addChefButtonAction(ActionEvent event) {
        try {
            boolean isAdded = chefService.addChef(new ChefDTO(
                    chefNameText.getText(),
                    chefNICText.getText(),
                    chefAddressText.getText(),
                    "chef : "+chefUserNameText.getText(),
                    chefPasswordText.getText()
            ));

            if(isAdded){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Chef Added!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                loadChefs();
                clearFields();
                chefNameText.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void chefAddressTextAction(ActionEvent event) {
        chefUserNameText.requestFocus();
    }

    @FXML
    void chefNICTextAction(ActionEvent event) {
        if(Validation.nicValidate(chefNICText.getText())) {
            chefAddressText.requestFocus();
        }else{
            chefNICText.selectAll();
        }
    }

    @FXML
    void chefNameTextAction(ActionEvent event) {
        if(Validation.nameValidate(chefNameText.getText())) {
            chefNICText.requestFocus();
        }
    }

    @FXML
    void chefPasswordTextAction(ActionEvent event) {
        addChefButtonAction(event);
    }

    @FXML
    void chefTableClicked(MouseEvent event) {
        ChefDTO selectedItem = chefTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            chefId = selectedItem.getChefId();
            chefNameText.setText(selectedItem.getChefName());
            chefNICText.setText(selectedItem.getChefNIC());
            chefAddressText.setText(selectedItem.getChefAddress());
            chefUserNameText.setText(selectedItem.getChefUserName());
            chefPasswordText.setText(selectedItem.getChefPassword());
            addChefButton.setDisable(true);
            updateChefButton.setDisable(false);
            deleteChefButton.setDisable(false);
        }
    }

    @FXML
    void chefUserNameTextAction(ActionEvent event) {
        chefPasswordText.requestFocus();
    }

    @FXML
    void deleteChefButtonAction(ActionEvent event) throws Exception {

        boolean b = chefService.deleteChef(chefId);
        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Chef Deleted!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadChefs();
            clearFields();
        }
    }

    private void clearFields() {
        addChefButton.setDisable(false);
        updateChefButton.setDisable(true);
        deleteChefButton.setDisable(true);
        chefNameText.clear();
        chefNICText.clear();
        chefAddressText.clear();
        chefUserNameText.clear();
        chefPasswordText.clear();
    }

    @FXML
    void searchTextKeyReleased(KeyEvent event) {
        searchChefText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchChefText.textProperty().get().isEmpty()) {
                    chefTable.setItems(chefDTOS);
                    return;
                }
                ObservableList<ChefDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<ChefDTO, ?>> cols = chefTable.getColumns();

                for (int i = 0; i < chefDTOS.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(chefDTOS.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchChefText.textProperty().get().toLowerCase())) {
                            tableItem.add(chefDTOS.get(i));
                            break;
                        }
                    }
                }
                chefTable.setItems(tableItem);
            }
        });
    }

    @FXML
    void updateChefButtonAction(ActionEvent event) throws Exception {
        boolean b = chefService.updateChef(new ChefDTO(chefId,
                chefNameText.getText(),
                chefNICText.getText(),
                chefAddressText.getText(),
                "chef : " + chefUserNameText.getText(),
                chefPasswordText.getText()
        ));
        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Chef Updated!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadChefs();
            clearFields();
        }
    }

}
