package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField EmailField;
    @FXML
    private CheckBox managerCheckBox;
    @FXML
    private PasswordField passwordField;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public  void onSignUpClicked(ActionEvent event){
        try {
            ((Node)(event.getSource())).getScene().getWindow().hide();
            SignUpController signUp=new SignUpController();
            Stage stage =new Stage();
            FXMLLoader signUpFxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
            signUpFxmlLoader.setController(signUp);
            Scene scene = new Scene(signUpFxmlLoader.load(), 800, 500);
            stage.setTitle("Sign Up");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void onLogInClicked(ActionEvent event){

        boolean isManager=managerCheckBox.isSelected();
        String email=EmailField.getText();
        String password=passwordField.getText();
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher isValidEmail = pattern.matcher(email); // add erroe msg
        boolean logged =false;
        if(isManager){
            logged=true;
        }else{
            logged=true;
        }
        if(logged) {
            try {
                ((Node)(event.getSource())).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader loggedInFxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoggedInCustomer.fxml"));
                LoggedInCustomerController controller=new LoggedInCustomerController();
                loggedInFxmlLoader.setController(controller);
                Scene scene = new Scene(loggedInFxmlLoader.load(), 200, 300);
                stage.setTitle("welcome customer");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
