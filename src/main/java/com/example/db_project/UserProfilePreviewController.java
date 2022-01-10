package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import service.UserValidator;

public class UserProfilePreviewController {
    private boolean stateChanged=false;
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
        User logged=new User();
        logged.firstName="test";
        logged.lastName="tesfas";
        logged.shippingAddress="safsgh";
        logged.phoneNumber="1841";
        logged.email="yousa@yahoo.com";
        firstName.setText(logged.firstName);
        lastName.setText(logged.lastName);
        Email.setText(logged.email);
        phoneNumber.setText(logged.phoneNumber);
        shippingAddress.setText(logged.shippingAddress);
    }
    public User getUserInfo(){
        //get user from apllication logic
    return null;
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
            }else {
                //show error msg
            }

        }
    }
    public void changePassword(){
        WindowChanger changer=new WindowChanger();
        ChangePasswordController controller=new ChangePasswordController();
        changer.changeWindow("ChangePasswordPanel",controller);
    }
}
