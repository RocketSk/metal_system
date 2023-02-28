package com.system.controller.orderController;

import com.system.interfaceForController.TabGetWindow;
import com.system.model.Customer;
import com.system.model.Order;
import com.system.model.Product;
import com.system.service.CompanyService;
import com.system.service.CustomerService;
import com.system.service.OrderService;
import com.system.service.ProductService;
import com.system.service.impl.CompanyServiceImpl;
import com.system.service.impl.CustomerServiceImpl;
import com.system.service.impl.OrderServiceImpl;
import com.system.service.impl.ProductServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderMainTabController extends TabGetWindow implements Initializable {

    private final static String URL_GET_ALL_ORDER_FXML = "/view/order/allOrders.fxml";
    private final static String URL_DELETE_ORDER_FXML = "/view/order/deleteOrder.fxml";

    private final static String GET_ALL_ORDER_TITLE = "Все заявки";
    private final static String DELETE_ORDER_TITLE = "Удалить заявку";


    private final CustomerService customerService = new CustomerServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final CompanyService companyService = new CompanyServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private final ObservableList<String> productObservableListToCart = FXCollections.observableArrayList();
    private final List<Product> productCart = new ArrayList<>();

    @FXML
    private ComboBox<Customer> customerComboBox;

    @FXML
    private HBox customerHBox;

    @FXML
    private Text customerIDHBox;

    @FXML
    private Text customerNameHBox;

    @FXML
    private ListView<String> productCartListView;

    @FXML
    private ComboBox<Product> productComboBox;

    @FXML
    private TextField productCountField;

    @FXML
    void addOrder(ActionEvent event) {
        Order order = Order.builder()
                .company(companyService.getAll().get(0))
                .customer(customerService.getById(Integer.parseInt(customerIDHBox.getText())))
                .build();
        orderService.addOrder(productCart, order);
        deleteProductFromCart(event);
    }

    @FXML
    void addProductToCart(ActionEvent event) {
        Product product = productComboBox.getSelectionModel().getSelectedItem();
        product.setCount(Integer.parseInt(productCountField.getText()));
        productCart.add(product);
        productObservableListToCart.add(
                "Продукт: " + product.getName() +
                        ", количество: " + product.getCount() +
                        ", цена за шт: " + product.getPrice() +
                        ", цена за позицию: " + product.getPrice()*product.getCount()
        );
    }

    @FXML
    void deleteOrder(ActionEvent event) {
        getWindow(URL_DELETE_ORDER_FXML, DELETE_ORDER_TITLE);
    }

    @FXML
    void getAllOrder(ActionEvent event) {
        getWindow(URL_GET_ALL_ORDER_FXML, GET_ALL_ORDER_TITLE);
    }

    @FXML
    void deleteProductFromCart(ActionEvent event) {
        if (!productCart.isEmpty()){
            productCart.clear();
            productObservableListToCart.clear();
        }
    }

    @FXML
    void findCustomer(ActionEvent event) {
        customerNameHBox.setVisible(true);
        Customer customer = customerComboBox.getSelectionModel().getSelectedItem();
        customerNameHBox.setText(customer.getName());
        customerIDHBox.setText(customer.getId().toString());
        customerHBox.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(customerService.getAll());
        ObservableList<Product> productObservableList = FXCollections.observableArrayList(productService.getAll());
        productComboBox.setItems(productObservableList);
        customerComboBox.setItems(customerObservableList);
        customerHBox.setVisible(false);
        productCartListView.setItems(productObservableListToCart);
    }
}