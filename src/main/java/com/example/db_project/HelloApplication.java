package com.example.db_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ApplicationLogic;
import service.DBConnection;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            DBConnection dbConnection = new DBConnection();
            ApplicationLogic applicationLogic = ApplicationLogic.getInstance();
            applicationLogic.initializeApplication(dbConnection);

            applicationLogic.userManager.login("adel@shakal.com", "Password@1");
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