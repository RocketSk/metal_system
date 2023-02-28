package com.system.controller.customerController;

import com.system.model.Customer;
import com.system.service.CustomerService;
import com.system.service.impl.CustomerServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DeleteCustomerController {

    private final CustomerService customerService = new CustomerServiceImpl();

    @FXML
    private TextField customerIdSearchField;

    @FXML
    private ListView<String> customerInfoListView;

    @FXML
    private TextField customerPhoneSearchField;

    @FXML
    void Cancel(ActionEvent event) {

    }

    @FXML
    void deleteCustomer(ActionEvent event) {
        customerService.delete(customerService.getById(Integer.parseInt(customerIdSearchField.getText())));
    }

    @FXML
    void findById(ActionEvent event) {
        showCustomer(customerService.getById(Integer.parseInt(customerIdSearchField.getText())));
    }

    @FXML
    void findByPhone(ActionEvent event) {
        showCustomer(customerService.getByPhone(customerPhoneSearchField.getText()));
    }

    private void showCustomer(Customer customer){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("id клиента: " + customer.getId());
        observableList.add("Название компании клиента: " + customer.getName());
        observableList.add("Номер телефона клиента: " + customer.getPhoneNumber());
        observableList.add("Адрес компании клиента: " + customer.getAddress());
        observableList.add("УМП/ИНН клиента: " + customer.getInn());
        observableList.add("Рассчетный счет клиента: " + customer.getIban());
        customerInfoListView.setItems(observableList);
    }

}
