package com.system.controller.orderController;

import com.system.model.Order;
import com.system.model.OrderDetails;
import com.system.model.Product;
import com.system.service.OrderDetailsService;
import com.system.service.OrderService;
import com.system.service.impl.OrderDetailsServiceImpl;
import com.system.service.impl.OrderServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class DeleteOrderController {

    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    @FXML
    private TextField orderIdField;

    @FXML
    private ListView<String> orderListView;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) orderIdField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void deleteOrder(ActionEvent event) {
        orderService.deleteOrder(orderService.getById(Integer.parseInt(orderIdField.getText())));
    }

    @FXML
    void searchById(ActionEvent event) {
        showInfo();
    }

    private void showInfo(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        OrderDetails orderDetails = orderDetailsService.getById(Integer.parseInt(orderIdField.getText()));
        List<Product> productList = orderDetails.getProductList();
        observableList.add("ID заявки : " + orderDetails.getOrder().getId());
        observableList.add("Заказчик : " + orderDetails.getCustomer());
        observableList.add("Заказанные товары : \n");
        for(Product product : productList){
            observableList.add("    " + product + " по цене за шт : " + product.getPrice());
        }
        observableList.add("Итого : " + orderDetails.getCoast());
        orderListView.setItems(observableList);
    }
}