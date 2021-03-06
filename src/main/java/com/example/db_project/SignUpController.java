package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.UserRole;
import service.ApplicationLogic;
import service.UserManager;
import service.UserValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {
    UserManager userManager= ApplicationLogic.getInstance().userManager;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField shippingAddress;
    @FXML
    public void regiesterIsClicked(ActionEvent event){
      WindowChanger changer=new WindowChanger();
        UserValidator validator=new UserValidator();
        String email=Email.getText();
        String Password=password.getText();
        String Address=shippingAddress.getText();
        String number=phoneNumber.getText();
        String FName=firstName.getText();
        String LName=lastName.getText();
        boolean validInputs=  validator.checkFieldsCorrectness(email,Password,number,LName,FName,Address);


        boolean registered=false;
        if(validInputs){
            //check values and change registered to true and regieste the user
            User newUser=new User();
            newUser.email=email;
            newUser.phoneNumber=number;
            newUser.shippingAddress=Address;
            newUser.firstName=FName;
            newUser.lastName=LName;
            newUser.role= UserRole.getUserRole(false);
            try {
                userManager.insertUser(newUser,Password);
                registered=true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //implement me

        }else{
            //show error msg
            changer.createMSGWindow("INValid inputs");

        }
        if(registered) {
                ((Node)(event.getSource())).getScene().getWindow().hide();
               changer.changeWindow("hello-view.fxml",null);

        }
    }
}
