package com.system.controller.companyController;

import com.system.model.Company;
import com.system.service.CompanyService;
import com.system.service.impl.CompanyServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCompanyController implements Initializable {

    private final CompanyService companyService = new CompanyServiceImpl();

    @FXML
    private TextField companyAddressField;

    @FXML
    private TextField companyINNField;

    @FXML
    private TextField companyIbanField;

    @FXML
    private TextField companyPhoneField;

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage = (Stage) companyPhoneField.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updateCompany(ActionEvent event) {
        Company company = companyService.getAll().get(0);
        company.setAddress(companyAddressField.getText());
        company.setInn(companyINNField.getText());
        company.setIban(companyIbanField.getText());
        company.setPhoneNumber(companyPhoneField.getText());
        companyService.update(company);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Company company = companyService.getAll().get(0);
        companyIbanField.setText(company.getIban());
        companyINNField.setText(company.getInn());
        companyAddressField.setText(company.getAddress());
        companyPhoneField.setText(company.getPhoneNumber());
    }
}
