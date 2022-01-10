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
import model.User;
import service.ApplicationLogic;
import service.UserManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    UserManager userManager = ApplicationLogic.getInstance().userManager;
    WindowChanger changer=new WindowChanger();
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
        Integer role = managerCheckBox.isSelected() ? 1 : 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher isValidEmail = pattern.matcher(email); // add erroe msg

        if (!isValidEmail.find()){
            AlertMessage.showError("Invalid Email");
            return;
        }


        User user = null;
        try {
            user = userManager.login(email, password, role);
            if (user != null) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                changer.changeWindow("LoggedInUser.fxml", null);
            }
            else
                AlertMessage.showError("Invalid Credentials");
        } catch (SQLException throwables) {
            AlertMessage.showError("Failed to execute query");
        }


    }
}
