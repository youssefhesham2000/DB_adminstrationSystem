package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserValidator;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController {
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
            //implement me
            registered=true;
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
