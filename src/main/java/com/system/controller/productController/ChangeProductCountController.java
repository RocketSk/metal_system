package com.system.controller.productController;

import com.system.model.Product;
import com.system.model.ProductGroup;
import com.system.service.ProductService;
import com.system.service.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeProductCountController implements Initializable {

    private final ProductService productService = new ProductServiceImpl();
    @FXML
    private TextField plusField;

    @FXML
    private TableColumn<Product, String> productArticleColumn;

    @FXML
    private TextField productArticleField;

    @FXML
    private TableColumn<Product, Integer> productCountColumn;

    @FXML
    private TableColumn<Product, ProductGroup> productGroupColumn;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TextField productIdField;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TextField subField;

    @FXML
    void findByArticle(ActionEvent event) {
        showProduct(productService.getByArticle(productArticleField.getText()));
    }

    @FXML
    void findById(ActionEvent event) {
        showProduct(productService.getById(Integer.parseInt(productIdField.getText())));
    }

    @FXML
    void plusProductCount(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (Integer.parseInt(plusField.getText()) > 0) {
            Product product = productService.getByArticle(productArticleField.getText());
            product.setCount(product.getCount() + Integer.parseInt(plusField.getText()));
            productService.update(product);
            showProduct(product);
            alert.setHeaderText("Успешно");
            alert.setContentText("Вы увеличили количество на складе");
            alert.showAndWait();
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText("введите корректное число");
            alert.showAndWait();
        }
    }

    @FXML
    void subProductCount(ActionEvent event) {
        Product product = productService.getByArticle(productArticleField.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (Integer.parseInt(subField.getText()) < product.getCount()) {
            product.setCount(product.getCount() - Integer.parseInt(subField.getText()));
            productService.update(product);
            showProduct(product);
            alert.setHeaderText("Успешно");
            alert.setContentText("Вы вычли товар со склада");
            alert.showAndWait();
        } else {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setHeaderText("Ошибка");
            alert.setContentText("Такого количества товара нету на складе");
            alert.showAndWait();
        }
    }

    @FXML
    void validationInteger(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
            plusField.setStyle("-fx-border-color: red");
            productIdField.setStyle("-fx-border-color: red");
            subField.setStyle("-fx-border-color: red");
        } else {
            plusField.setStyle("-fx-border-color: blue");
            productIdField.setStyle("-fx-border-color: blue");
            subField.setStyle("-fx-border-color: blue");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        productArticleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productGroupColumn.setCellValueFactory(new PropertyValueFactory<>("productGroup"));
    }

    private void showProduct(Product product){
        ObservableList<Product> observableList = FXCollections.observableArrayList();
        productArticleField.setText(product.getArticle());
        observableList.add(product);
        productTableView.setItems(observableList);
    }
}