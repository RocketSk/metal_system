package com.system.controller.productController;

import com.system.model.Product;
import com.system.service.ProductService;
import com.system.service.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteProductController {

    private final ProductService productService = new ProductServiceImpl();

    @FXML
    public TextField productArticleField;

    @FXML
    public TextField productIdField;

    @FXML
    private ListView<String> productListView;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) productListView.getScene().getWindow();
        stage.close();
    }

    @FXML
    void delete(ActionEvent event) {
        productService.delete(productService.getByArticle(productArticleField.getText()));
    }

    @FXML
    void findByArticle(ActionEvent event) {
        showProductInfo(productService.getByArticle(productArticleField.getText()));
    }

    @FXML
    void findById(ActionEvent event) {
        showProductInfo(productService.getById(Integer.parseInt(productIdField.getText())));
    }

    private void showProductInfo(Product product){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("ID продукта: " + product.getId());
        observableList.add("Название продукта: " + product.getName());
        observableList.add("Цена продукта: " + product.getPrice());
        observableList.add("Продуктовая группа: " + product.getProductGroup());
        observableList.add("Артикул продукта: " + product.getArticle());
        observableList.add("Количество на складе: " + product.getCount());
        productListView.setItems(observableList);
        productArticleField.setText(product.getArticle());
    }
}
