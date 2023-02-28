package com.system.controller.productController;

import com.system.model.Product;
import com.system.model.ProductGroup;
import com.system.service.ProductGroupService;
import com.system.service.ProductService;
import com.system.service.impl.ProductGroupServiceImpl;
import com.system.service.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    private final ProductService productService = new ProductServiceImpl();
    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();

    @FXML
    private TextField productArticleField;

    @FXML
    private ComboBox<ProductGroup> productGroupComboBox;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField productPriceField;

    @FXML
    private TextField productStartCountField;

    @FXML
    void addProduct(ActionEvent event) {
        productService.addProduct(Product.builder()
                .article(productArticleField.getText())
                .productGroup(productGroupComboBox.getSelectionModel().getSelectedItem())
                .count(Integer.parseInt(productStartCountField.getText()))
                .price(Double.parseDouble(productPriceField.getText()))
                .name(productNameField.getText())
                .build());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Продукт успешно добавлен");
        alert.showAndWait();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) productStartCountField.getScene().getWindow();
        stage.close();
    }


    @FXML
    void integerCheckInput(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
            productStartCountField.setStyle("-fx-border-color: red");
        } else {
            productStartCountField.setStyle("-fx-border-color: blue");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showProductGroupToBox();
    }

    private void showProductGroupToBox() {
        ObservableList<ProductGroup> observableList = FXCollections.observableArrayList(productGroupService.getAll());
        productGroupComboBox.setItems(observableList);
    }
}
