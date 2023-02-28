package com.system.controller.customerController;

import com.system.model.Customer;
import com.system.service.CustomerService;
import com.system.service.impl.CustomerServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateCustomerController {

    private final CustomerService customerService = new CustomerServiceImpl();

    @FXML
    public TextField customerAddressField;

    @FXML
    public TextField customerPhoneSearchField;

    @FXML
    public TextField customerIdSearchField;

    @FXML
    private TextField customerINNField;

    @FXML
    private TextField customerIbanField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerPhoneField;

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage = (Stage) customerIbanField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updateCustomer(ActionEvent event) {
        Customer customer = customerService.getById(Integer.parseInt(customerIdSearchField.getText()));
        customer.setName(customerNameField.getText());
        customer.setAddress(customerAddressField.getText());
        customer.setInn(customerINNField.getText());
        customer.setPhoneNumber(customerPhoneField.getText());
        customer.setIban(customerIbanField.getText());
        customerService.update(customer);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Вы успешно изменили данные \n клиента");
        alert.showAndWait();
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
        customerIdSearchField.setText(customer.getId().toString());
        customerIbanField.setText(customer.getIban());
        customerNameField.setText(customer.getName());
        customerINNField.setText(customer.getInn());
        customerAddressField.setText(customer.getAddress());
        customerPhoneField.setText(customer.getPhoneNumber());
    }

}
