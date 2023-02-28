package com.system.controller.productController;

import com.system.interfaceForController.TabGetWindow;
import com.system.model.Product;
import com.system.service.ProductService;
import com.system.service.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainProductTabController extends TabGetWindow implements Initializable {

    private final static String URL_ADD_PRODUCT_FXML = "/view/product/addProduct.fxml";
    private final static String URL_UPDATE_PRODUCT_FXML = "/view/product/updateProduct.fxml";
    private final static String URL_DELETE_PRODUCT_FXML = "/view/product/deleteProduct.fxml";
    private final static String URL_GET_PRODUCT_FXML = "/view/product/getProduct.fxml";
    private final static String URL_CHANGE_PRODUCT_COUNT_FXML = "/view/product/changeProductCount.fxml";

    private final static String ADD_PRODUCT_TITLE = "Добавить продукцию";
    private final static String UPDATE_PRODUCT_TITLE = "Обновить продукт";
    private final static String DELETE_PRODUCT_TITLE = "Удалить продукцию";
    private final static String GET_PRODUCT_TITLE = "Поиск продукции";
    private final static String CHANGE_PRODUCT_COUNT_TITLE = "Остатки продукции";



    private final ProductService productService = new ProductServiceImpl();

    @FXML
    private TableColumn<Product, Integer> productCountColumn;

    @FXML
    private TableColumn<Product, String> productArticleColumn;

    @FXML
    private TableColumn<Product, Group> productGroupColumn;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    void addProduct(ActionEvent event) {
        getWindow(URL_ADD_PRODUCT_FXML, ADD_PRODUCT_TITLE);
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        getWindow(URL_DELETE_PRODUCT_FXML, DELETE_PRODUCT_TITLE);
    }

    @FXML
    void updateProduct(ActionEvent event) {
        getWindow(URL_UPDATE_PRODUCT_FXML, UPDATE_PRODUCT_TITLE);
    }

    @FXML
    void Search(ActionEvent event) {
        getWindow(URL_GET_PRODUCT_FXML, GET_PRODUCT_TITLE);
    }

    @FXML
    void changeProductCount(ActionEvent event) {
        getWindow(URL_CHANGE_PRODUCT_COUNT_FXML, CHANGE_PRODUCT_COUNT_TITLE);
    }

    @FXML
    void updateTableView(ActionEvent event) {
        showProduct();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showProduct();
    }

    private void showProduct(){
        ObservableList<Product> observableList = FXCollections.observableArrayList(productService.getAll());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //RocketSk
        productArticleColumn.setCellValueFactory(new PropertyValueFactory<>("article"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productGroupColumn.setCellValueFactory(new PropertyValueFactory<>("productGroup"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productCountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        productTableView.setItems(observableList);
    }
}
