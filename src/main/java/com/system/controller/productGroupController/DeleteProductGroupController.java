package com.system.controller.productGroupController;

import com.system.model.ProductGroup;
import com.system.service.ProductGroupService;
import com.system.service.impl.ProductGroupServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteProductGroupController implements Initializable {

    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();

    @FXML
    private ComboBox<ProductGroup> productGroupComboBox;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) productGroupComboBox.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteProductGroup(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(productGroupComboBox.getSelectionModel().getSelectedItem() != null){
            productGroupService.delete(productGroupComboBox.getSelectionModel().getSelectedItem());
            alert.setHeaderText("Успешно");
            alert.setContentText("Вы успешно удалили группу \n товаров");
            alert.showAndWait();
        } else {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Выберите группу для удаления");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showProductGroup();
    }

    private void showProductGroup(){
        ObservableList<ProductGroup> observableList = FXCollections.observableArrayList(productGroupService.getAll());
        productGroupComboBox.setItems(observableList);
    }
}
