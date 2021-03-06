package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.User;
import service.ApplicationLogic;
import service.CartManager;
import service.UserManager;

import java.sql.SQLException;

public class CreditCardController {
    WindowChanger changer=new WindowChanger();
    @FXML
    private TextField creditCardNumber;
    @FXML
    private DatePicker expiryDate;
    CartManager cartManager= ApplicationLogic.getInstance().cartManager;
    UserManager userManager= ApplicationLogic.getInstance().userManager;
    User logged=ApplicationLogic.getInstance().loggedInUser;
    public void checkCreditCard(ActionEvent event){//need to connect this with the cart so it doesn't accept untill here is true
        long number=Long.valueOf(creditCardNumber.getText());
        GUIUtils utils=new GUIUtils();
        if(utils.isValid(number)){
            //implement me
            //perform update on DB then clear user cart
            boolean sucess=false;
            try {
                userManager.buyCart(logged);
            } catch (SQLException e) {
                changer.createMSGWindow("purchase failed try again");
            }

            changer.changeWindow("Cart.fxml",null);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            //add msg if we have time

        }
        else {
            changer.changeWindow("Cart.fxml",null);
            changer.createMSGWindow("Invalid card");
            ((Node)(event.getSource())).getScene().getWindow().hide();

        }
    }
}
