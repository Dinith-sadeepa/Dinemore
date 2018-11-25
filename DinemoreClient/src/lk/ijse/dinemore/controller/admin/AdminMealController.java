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
import lk.ijse.dinemore.dto.MealDTO;
import lk.ijse.dinemore.proxy.ProxyHandler;
import lk.ijse.dinemore.service.ServiceFactory;
import lk.ijse.dinemore.service.custom.MealService;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminMealController {
    @FXML
    private TextField searchMealText;

    @FXML
    private TableView<MealDTO> mealTable;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private JFXTextField mealNameText;

    @FXML
    private JFXTextField mealUnitPriceText;

    @FXML
    private JFXTextField mealCategoryText;

    @FXML
    private JFXTextField mealQtyText;

    @FXML
    private JFXButton addMealButton;

    @FXML
    private JFXButton deleteMealButton;

    @FXML
    private JFXButton updateMealButton;

    private MealService mealService;
    private ObservableList<MealDTO> mealDTOS;
    private int mealId;

    public void initialize() {
        dateLabel.setText(new SimpleDateFormat("YYYY-mm-dd").format(new Date()));
        timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));

        try {
            mealService = (MealService) ProxyHandler.getInstance().getService(ServiceFactory.ServiceTypes.MEAL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadColums();
        loadMeals();
    }

    private void loadMeals() {
        try {
            ArrayList<MealDTO> allMeal = (ArrayList<MealDTO>) mealService.getAllMeal();
            mealDTOS = FXCollections.observableArrayList(allMeal);
            mealTable.setItems(mealDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadColums() {
        mealTable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("mealId"));
        mealTable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("mealName"));
        mealTable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mealCategory"));
        mealTable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mealUnitPrice"));
    }

    @FXML
    void addMealButtonAction(ActionEvent event) {
        try {
            boolean isAdded = mealService.addMeal(new MealDTO(mealNameText.getText(),
                    mealCategoryText.getText(),
                    Double.parseDouble(mealUnitPriceText.getText())));

            if(isAdded){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Meal Added!", ButtonType.OK);
                a.setHeaderText(null);
                a.showAndWait();
                loadMeals();
                clearFields();
                mealNameText.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteMealButtonAction(ActionEvent event) throws Exception {
        boolean b = mealService.deleteMeal(mealId);
        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Meal Deleted!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadMeals();
            clearFields();
        }
    }

    @FXML
    void mealCategoryTextAction(ActionEvent event) {
        mealUnitPriceText.requestFocus();
    }

    @FXML
    void mealNameTextAction(ActionEvent event) {
        mealCategoryText.requestFocus();
    }

    @FXML
    void mealUnitPriceTextAction(ActionEvent event) {
        if(Validation.integerValueValidate(mealUnitPriceText.getText()) || Validation.pricesValidate(mealUnitPriceText.getText())) {
            addMealButtonAction(event);
        }else{
            mealUnitPriceText.selectAll();
        }
    }

    @FXML
    void searchTextKeyReleased(KeyEvent event) {
        searchMealText.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (searchMealText.textProperty().get().isEmpty()) {
                    mealTable.setItems(mealDTOS);
                    return;
                }
                ObservableList<MealDTO> tableItem = FXCollections.observableArrayList();
                ObservableList<TableColumn<MealDTO, ?>> cols = mealTable.getColumns();
                for (int i = 0; i < mealDTOS.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(mealDTOS.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchMealText.textProperty().get().toLowerCase())) {
                            tableItem.add(mealDTOS.get(i));
                            System.out.println();
                            break;
                        }
                    }
                }
                mealTable.setItems(tableItem);
            }
        });
    }

    @FXML
    void updateMealButtonAction(ActionEvent event) throws Exception {
        boolean b = mealService.updateMeal(new MealDTO(mealId, mealNameText.getText(),
                mealCategoryText.getText(),
                Double.parseDouble(mealUnitPriceText.getText())));

        if(b){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Meal Updated!", ButtonType.OK);
            a.setHeaderText(null);
            a.showAndWait();
            loadMeals();
            clearFields();
        }
    }

    @FXML
    void mealTableClicked(MouseEvent event) {
        MealDTO selectedItem = mealTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            mealId = selectedItem.getMealId();
            mealNameText.setText(selectedItem.getMealName());
            mealCategoryText.setText(selectedItem.getMealCategory());
            mealUnitPriceText.setText(selectedItem.getMealUnitPrice()+"");

            addMealButton.setDisable(true);
            updateMealButton.setDisable(false);
            deleteMealButton.setDisable(false);
        }
    }
    private void clearFields() {
        mealUnitPriceText.clear();
        addMealButton.setDisable(false);
        updateMealButton.setDisable(true);
        deleteMealButton.setDisable(true);
        mealNameText.clear();
        mealCategoryText.clear();
    }
}
