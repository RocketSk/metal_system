package com.system.controller.productGroupController;

import com.system.interfaceForController.TabGetWindow;
import com.system.model.ProductGroup;
import com.system.service.ProductGroupService;
import com.system.service.impl.ProductGroupServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainProductGroupTabController extends TabGetWindow implements Initializable {

    private final static String URL_ADD_PRODUCT_GROUP_FXML = "/view/productGroup/addProductGroup.fxml";
    private final static String URL_UPDATE_PRODUCT_GROUP_FXML = "/view/productGroup/updateProductGroup.fxml";
    private final static String URL_DELETE_PRODUCT_GROUP_FXML = "/view/productGroup/deleteProductGroup.fxml";

    private final static String ADD_PRODUCT_GROUP_TITLE = "Добавить группу товаров";
    private final static String UPDATE_PRODUCT_GROUP_TITLE = "Обновить группу товаров";
    private final static String DELETE_PRODUCT_GROUP_TITLE = "Удалить группу товаров";

    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();

    @FXML
    private TableColumn<ProductGroup, Integer> productIdColumn;

    @FXML
    private TableColumn<ProductGroup, String> productNameColumn;

    @FXML
    private TableView<ProductGroup> productTableView;

    @FXML
    void addProductGroup(ActionEvent event) {
        getWindow(URL_ADD_PRODUCT_GROUP_FXML, ADD_PRODUCT_GROUP_TITLE);
    }

    @FXML
    void deleteProductGroup(ActionEvent event) {
        getWindow(URL_DELETE_PRODUCT_GROUP_FXML, DELETE_PRODUCT_GROUP_TITLE);
    }

    @FXML
    void updateProductGroup(ActionEvent event) {
        getWindow(URL_UPDATE_PRODUCT_GROUP_FXML, UPDATE_PRODUCT_GROUP_TITLE);
    }

    @FXML
    void updateTableView(ActionEvent event) {
        showProductGroup();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        showProductGroup();
    }

    private void showProductGroup(){
        ObservableList<ProductGroup> observableList = FXCollections.observableArrayList(productGroupService.getAll());
        productTableView.setItems(observableList);
    }
}
