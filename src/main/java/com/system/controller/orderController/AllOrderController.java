package com.system.controller.orderController;

import com.system.model.Customer;
import com.system.model.OrderDetails;
import com.system.model.Product;
import com.system.service.OrderDetailsService;
import com.system.service.OrderService;
import com.system.service.impl.OrderDetailsServiceImpl;
import com.system.service.impl.OrderServiceImpl;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllOrderController implements Initializable {

    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();

    @FXML
    private TableColumn<OrderDetails, ArrayList<Product>> orderCartColumn;

    @FXML
    private TableColumn<OrderDetails, Double> orderCoastColumn;

    @FXML
    private TableColumn<OrderDetails, Customer> orderCustomerColumn;

    @FXML
    private TableColumn<OrderDetails, Integer> orderIdColumn;

    @FXML
    private TableView<OrderDetails> orderTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("я тут");
        showInfo();
    }

    private void showInfo(){
        ObservableList<OrderDetails> observableList = FXCollections.observableArrayList(orderDetailsService.getAllOrderDetails());
        System.out.println(observableList);
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        orderCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        orderCartColumn.setCellValueFactory(new PropertyValueFactory<>("productList"));
        orderCoastColumn.setCellValueFactory(new PropertyValueFactory<>("coast"));
        orderTableView.setItems(observableList);
    }
}
