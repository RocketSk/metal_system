package com.system.controller.printController;

import com.system.contractDetails.Contract;
import com.system.model.OrderDetails;
import com.system.model.Product;
import com.system.service.OrderDetailsService;
import com.system.service.impl.OrderDetailsServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class PrintMainTabController {

    private final OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl();
    @FXML
    private Label jobStatus;

    @FXML
    private TextArea documentTextArea;

    @FXML
    private TextField orderIdField;

    @FXML
    private ListView<String> orderListView;

    @FXML
    void getOrderContract(ActionEvent event) {
        Contract contract = new Contract();
        documentTextArea.setText(contract.getSoldContract(getOrderDetails()));
    }

    @FXML
    void getOrderDocument(ActionEvent event) {
        Contract contract = new Contract();
        documentTextArea.setText(contract.getOrderContract(getOrderDetails()));
    }

    @FXML
    void printDocument(ActionEvent event) {
        print(documentTextArea);
    }

    @FXML
    void searchById(ActionEvent event) {
        OrderDetails orderDetails = getOrderDetails();
        ObservableList<String> observableList = FXCollections.observableArrayList();
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

    private void print(TextArea textArea) {
        Printer printer = Printer.getDefaultPrinter();
        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);
        String toPrint = textArea.getText();

        // Show the print setup dialog
        boolean proceed = printerJob.showPrintDialog(textArea.getScene().getWindow());
        if (proceed) {
            print(printerJob, new Label(toPrint));
        }
    }

    private OrderDetails getOrderDetails(){
        return orderDetailsService.getById(Integer.parseInt(orderIdField.getText()));
    }

    private void print(PrinterJob printerJob, Node node) {
        // Set the Job Status Message
        jobStatus.textProperty().bind(printerJob.jobStatusProperty().asString());

        // Print the node
        boolean printed = printerJob.printPage(node);

        if (printed) {
            printerJob.endJob();
        }
    }
}
