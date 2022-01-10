package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class ChangePasswordController {
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    public void changePassword(){
        //check and update DB password
        String currentPass=currentPassword.getText();
        String newPass=newPassword.getText();
    }
}
