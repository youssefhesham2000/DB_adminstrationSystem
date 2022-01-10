package com.example.db_project;

import javafx.scene.control.Alert;

public class AlertMessage {
    static void showError(String errorMessage){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(errorMessage);
        a.show();
    }

    static void showConfirmation(String confirmationMessage){
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(confirmationMessage);
        a.show();
    }
}
