package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        String email=Email.getText();
        String Password=password.getText();
        String Address=shippingAddress.getText();
        String number=phoneNumber.getText();
        String FName=firstName.getText();
        String LName=lastName.getText();
        boolean validInputs=  checkFieldsCorrectness(email,Password,number,LName,FName,Address);
        System.out.println(number.matches("[0-9]+"));
        System.out.println(FName.matches("[a-zA-Z]+"));
        System.out.println(email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"));
        System.out.println(LName.matches("[a-zA-Z]+"));
        System.out.println( Address.matches("[a-zA-Z0-9]*"));
        System.out.println(Password.length()>8);

        boolean registered=false;
        if(validInputs){
            //check values and change registered to true
            registered=true;
        }else{
            //show error msg
        }
        if(registered) {
            try {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
                HelloController instance=new HelloController();
                //fxmlLoader.setController(instance);
                Scene scene = new Scene(fxmlLoader.load(), 500, 400);
                stage.setTitle("Log In!");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkFieldsCorrectness(String email,String password,String number,String LName,String FName,String Address){
        return  number.matches("[0-9]+") && FName.matches("[a-zA-Z]+")&& email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")&&
                LName.matches("[a-zA-Z]+")&& Address.matches("[a-zA-Z0-9]*")&&password.length()>8;
    }
}
