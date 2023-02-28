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
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GetProductController implements Initializable {

    private final ProductService productService = new ProductServiceImpl();
    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();

    @FXML
    public ComboBox<ProductGroup> productGroupComboBox;

    @FXML
    private TextField articleField;

    @FXML
    private TextField idField;

    @FXML
    private TableColumn<Product, String> productArticleColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private TableColumn<Product, Integer> productCountColumn;

    @FXML
    private TableColumn<Product, ProductGroup> productGroupColumn;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    void findByArticle(ActionEvent event) {
        showProduct(productService.getByArticle(articleField.getText()));
    }

    @FXML
    void findByGroup(ActionEvent event) {
        ProductGroup group = productGroupComboBox.getSelectionModel().getSelectedItem();
        showProduct(productService.getAllByGroup(group));
    }

    @FXML
    void findById(ActionEvent event) {
        showProduct(productService.getById(Integer.parseInt(idField.getText())));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productArticleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        productGroupColumn.setCellValueFactory(new PropertyValueFactory<>("productGroup"));
        showProductGroup();
    }

    private void showProductGroup() {
        ObservableList<ProductGroup> observableList = FXCollections.observableArrayList(productGroupService.getAll());
        productGroupComboBox.setItems(observableList);
    }

    private void showProduct(Product product){
        ObservableList<Product> observableList = FXCollections.observableArrayList();
        observableList.add(product);
        productTableView.setItems(observableList);
    }

    private void showProduct(List<Product> productList){
        ObservableList<Product> observableList = FXCollections.observableArrayList(productList);
        productTableView.setItems(observableList);
    }
}
