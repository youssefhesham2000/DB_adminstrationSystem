module com.example.db_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.db_project to javafx.fxml;
    exports com.example.db_project;
}