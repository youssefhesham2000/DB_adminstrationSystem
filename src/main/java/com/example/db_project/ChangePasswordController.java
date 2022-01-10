package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.User;
import service.ApplicationLogic;
import service.BookManager;
import service.UserManager;

public class ChangePasswordController {
    private  WindowChanger changer=new WindowChanger();
    UserManager userManager = ApplicationLogic.getInstance().userManager;
    User logged=ApplicationLogic.getInstance().loggedInUser;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    public void changePassword(){
        //check password  if password is not right change Pass and update DB password
        //implement me
        boolean Pass=true;
        String currentPass=currentPassword.getText();
        String newPass=newPassword.getText();
        if(newPass.length()>8){
           /* if(logged.){

            }*/
        }
        String Msg= Pass&&newPass.length()>8?"updated Sucessfully":"Invalid inputs";
        changer.createMSGWindow(Msg);

    }
}
