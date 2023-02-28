package com.system.controller.customerController;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class GetCustomerController implements Initializable {

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
    private TextField idField;

    @FXML
    private TextField phoneField;

    @FXML
    void findById(ActionEvent event) {
        showCustomer(customerService.getById(Integer.parseInt(idField.getText())));
    }

    @FXML
    void findByPhone(ActionEvent event) {
        showCustomer(customerService.getByPhone(phoneField.getText()));
    }

    private void showCustomer(Customer customer){
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        observableList.add(customer);
        customerTableView.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerINNColumn.setCellValueFactory(new PropertyValueFactory<>("inn"));
        customerIbanColumn.setCellValueFactory(new PropertyValueFactory<>("iban"));
    }
}
