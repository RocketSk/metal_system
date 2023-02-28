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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateProductController {

    private final ProductService productService = new ProductServiceImpl();
    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();

    @FXML
    private TextField productArticleField;

    @FXML
    private TextField productArticleSearchField;

    @FXML
    private TextField productCountField;

    @FXML
    private TextField productPriceField;

    @FXML
    private ComboBox<ProductGroup> productGroupComboBox;

    @FXML
    private TextField productIDField;

    @FXML
    private TextField productNameField;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) productIDField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void findByArticle(ActionEvent event) {
        Product product = productService.getByArticle(productArticleSearchField.getText());
        showProductInfo(product);
    }

    @FXML
    void findById(ActionEvent event) {
        Product product = productService.getById(Integer.parseInt(productIDField.getText()));
        showProductInfo(product);
    }

    @FXML
    void updateProduct(ActionEvent event) {
        productService.update(Product.builder()
                .id(Integer.parseInt(productIDField.getText()))
                .name(productNameField.getText())
                .productGroup(productGroupComboBox.getSelectionModel().getSelectedItem())
                .count(Integer.parseInt(productCountField.getText()))
                .article(productArticleField.getText())
                .build());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Продукт успешно обновлен");
        alert.showAndWait();
    }

    private void showProductGroup(Product product){
        ObservableList<ProductGroup> observableList = FXCollections.observableArrayList(productGroupService.getAll());
        productGroupComboBox.setItems(observableList);
        productGroupComboBox.setValue(product.getProductGroup());
    }

    private void showProductInfo(Product product){
        productArticleField.setText(product.getArticle());
        productCountField.setText(product.getCount().toString());
        productNameField.setText(product.getName());
        productPriceField.setText(product.getPrice().toString());
        showProductGroup(product);
    }



}
