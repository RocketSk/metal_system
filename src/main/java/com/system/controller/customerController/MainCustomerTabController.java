package com.system.controller.customerController;

import com.system.interfaceForController.TabGetWindow;
import com.system.model.Customer;
import com.system.service.CustomerService;
import com.system.service.impl.CustomerServiceImpl;
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

public class MainCustomerTabController extends TabGetWindow implements Initializable {

    private final static String URL_ADD_CUSTOMER_FXML = "/view/customer/addCustomer.fxml";
    private final static String URL_UPDATE_CUSTOMER_FXML = "/view/customer/updateCustomer.fxml";
    private final static String URL_DELETE_CUSTOMER_FXML = "/view/customer/deleteCustomer.fxml";
    private final static String URL_GET_CUSTOMER_FXML = "/view/customer/getCustomer.fxml";

    private final static String ADD_CUSTOMER_TITLE = "Добавить клиента";
    private final static String UPDATE_CUSTOMER_TITLE = "Изменить клиента";
    private final static String DELETE_CUSTOMER_TITLE = "Удалить клиента";
    private final static String GET_CUSTOMER_TITLE = "Поиск клиента";

    private final CustomerService customerService = new CustomerServiceImpl();

    @FXML
    private TableColumn<Customer, String> customerAddressColumn;

    @FXML
    private TableColumn<Customer, String> customerINNColumn;

    @FXML
    private TableColumn<Customer, String> customerIbanColumn;

    @FXML
    private TableColumn<Customer, Integer> customerIdColumn;

    @FXML
    private TableColumn<Customer, String> customerNameColumn;

    @FXML
    private TableColumn<Customer, Integer> customerPhoneColumn;

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    void addCustomer(ActionEvent event) {
        getWindow(URL_ADD_CUSTOMER_FXML, ADD_CUSTOMER_TITLE);
    }

    @FXML
    void deleteCustomer(ActionEvent event) {
        getWindow(URL_DELETE_CUSTOMER_FXML, DELETE_CUSTOMER_TITLE);
    }

    @FXML
    void findCustomer(ActionEvent event) {
        getWindow(URL_GET_CUSTOMER_FXML, GET_CUSTOMER_TITLE);
    }

    @FXML
    void updateCustomer(ActionEvent event) {
        getWindow(URL_UPDATE_CUSTOMER_FXML, UPDATE_CUSTOMER_TITLE);
    }

    @FXML
    void updateTableView(ActionEvent event) {
        showCustomer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerINNColumn.setCellValueFactory(new PropertyValueFactory<>("inn"));
        customerIbanColumn.setCellValueFactory(new PropertyValueFactory<>("iban"));
        showCustomer();
    }

    private void showCustomer(){
        ObservableList<Customer> observableList = FXCollections.observableArrayList(customerService.getAll());
        customerTableView.setItems(observableList);
    }
}
