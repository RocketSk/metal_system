package com.system.controller.companyController;

import com.system.interfaceForController.TabGetWindow;
import com.system.model.Company;
import com.system.service.CompanyService;
import com.system.service.impl.CompanyServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CompanyTabController extends TabGetWindow implements Initializable {

    private final static String URL_UPDATE_COMPANY_FXML = "/view/company/updateCompany.fxml";

    private final static String UPDATE_COMPANY_TITLE = "Обновить данные";



    private final CompanyService companyService = new CompanyServiceImpl();

    @FXML
    private Text companyIban;

    @FXML
    private Text companyAddress;

    @FXML
    private Text companyInn;

    @FXML
    private Text companyName;

    @FXML
    private Text companyPhone;

    @FXML
    void updateCompany(ActionEvent event) {
        getWindow(URL_UPDATE_COMPANY_FXML, UPDATE_COMPANY_TITLE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Company company = companyService.getAll().get(0);

        companyName.setText(company.getName());
        companyPhone.setText(company.getPhoneNumber());
        companyAddress.setText(company.getAddress());
        companyInn.setText(company.getInn());
        companyIban.setText(company.getIban());
    }
}
