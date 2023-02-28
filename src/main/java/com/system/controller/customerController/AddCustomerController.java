package com.system.controller.customerController;

import com.system.model.Customer;
import com.system.service.CustomerService;
import com.system.service.impl.CustomerServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCustomerController {

    private final CustomerService customerService = new CustomerServiceImpl();

    @FXML
    private TextField customerAddressCityField;

    @FXML
    private TextField customerAddressHouseField;

    @FXML
    private TextField customerAddressPostField;

    @FXML
    private TextField customerAddressStreetField;

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
        Stage stage = (Stage) customerAddressPostField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addCustomer(ActionEvent event) {
        addCustomer();
    }

    private void addCustomer() {
        customerService.addCustomer(Customer.builder()
                .name(customerNameField.getText())
                .inn(customerINNField.getText())
                .iban(customerIbanField.getText())
                .phoneNumber(customerPhoneField.getText())
                .address(customerAddressCityField.getText() + ",\n" +
                        customerAddressStreetField.getText() + ",\n" +
                        customerAddressHouseField.getText() + ",\n" +
                        customerAddressPostField.getText()
                )
                .build()
        );
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Успешно");
        alert.setContentText("Улиент успешно добавлен");
        alert.showAndWait();
    }
}
