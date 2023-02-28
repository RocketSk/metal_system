module com.system {

    // Fx req
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.compiler;
    requires java.instrument;
    requires jdk.unsupported;

    // Database
    requires java.sql;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires javax.el.api;
    requires java.persistence;
    requires java.naming;

    // Fx core
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires lombok;

    opens com.system to javafx.fxml;
    exports com.system;
    exports com.system.controller.mainController;
    exports com.system.controller.productController;
    exports com.system.controller.productGroupController;
    exports com.system.controller.customerController;
    exports com.system.controller.companyController;
    exports com.system.controller.orderController;
    exports com.system.controller.printController;


    exports com.system.model;
    opens com.system.model to org.hibernate.orm.core;
    opens com.system.controller.mainController to javafx.fxml;
    opens com.system.controller.productController to javafx.fxml;
    opens com.system.controller.productGroupController to javafx.fxml;
    opens com.system.controller.customerController to javafx.fxml;
    opens com.system.controller.companyController to javafx.fxml;
    opens com.system.controller.orderController to javafx.fxml;
    opens com.system.controller.printController to javafx.fxml;
}

