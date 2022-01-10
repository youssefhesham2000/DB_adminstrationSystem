package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import service.ApplicationLogic;
import service.UserManager;
import service.UserValidator;

import java.sql.SQLException;

public class UserProfilePreviewController {
    ApplicationLogic applicationLogic = ApplicationLogic.getInstance();
    User logged = applicationLogic.loggedInUser;
    UserManager userManager = applicationLogic.userManager;

    private boolean stateChanged=false;
    WindowChanger changer=new WindowChanger();
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField Email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField shippingAddress;
    @FXML
    public void initialize(){
      firstName.setText(logged.firstName);
        lastName.setText(logged.lastName);
        Email.setText(logged.email);
        phoneNumber.setText(logged.phoneNumber);
        shippingAddress.setText(logged.shippingAddress);
    }

    public void setStateChanged(){
        stateChanged=true;
    }


    public void updateUserInfo(){
        if(stateChanged) {
            UserValidator validator = new UserValidator();
            if (validator.checkFieldsCorrectness(Email.getText(),"arbitarydata",phoneNumber.getText(),lastName.getText()
            ,firstName.getText(),shippingAddress.getText())) {
                User newUser = new User();
                newUser.email = Email.getText();
                newUser.firstName = firstName.getText();
                newUser.lastName = lastName.getText();
                newUser.phoneNumber = phoneNumber.getText();
                newUser.shippingAddress = shippingAddress.getText();

                try {
                    userManager.updateUser(logged, newUser);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }else {
                //show error msg
                changer.createMSGWindow("InValid Inputs");

            }

        }
    }
    public void changePassword(){
        changer.changeWindow("ChangePasswordPanel",new ChangePasswordController());
    }
}
