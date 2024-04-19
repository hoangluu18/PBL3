module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires jdk.compiler;


    opens View to javafx.fxml;
    exports View;
    exports Controller;
    opens Controller to javafx.fxml;
    exports Test;
    opens Test to javafx.fxml;
}