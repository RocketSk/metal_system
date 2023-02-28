package com.system.controller.productGroupController;

import com.system.model.ProductGroup;
import com.system.service.ProductGroupService;
import com.system.service.impl.ProductGroupServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProductGroupController {

    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();

    @FXML
    private TextField nameProductGroupField;

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage = (Stage) nameProductGroupField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addProductGroup(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (nameProductGroupField.getText() != null && !nameProductGroupField.getText().isEmpty()) {
            productGroupService.addProductGroup(
                    ProductGroup.builder()
                            .name(nameProductGroupField.getText()).build());
            alert.setHeaderText("Успешно");
            alert.setContentText("Вы добавили новую группу \n товаров");
            alert.showAndWait();
        } else {
            alert.setHeaderText("Ошибка");
            alert.setContentText("Введите название для группы \n товаров");
            alert.showAndWait();
        }
    }


}
