package com.example.db_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ApplicationLogic;
import service.DBConnection;
import service.UserManager;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            String url = "jdbc:mysql://156.192.255.112:3306/ORDER_PROCESSING_SYSTEM";
            DBConnection dbConnection = new DBConnection(url);
            ApplicationLogic applicationLogic = ApplicationLogic.getInstance();
            applicationLogic.initializeApplication(dbConnection);

            applicationLogic.userManager.login("adel@shakal.com", "Password@1", 1);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoggedInUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Library Administration System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}