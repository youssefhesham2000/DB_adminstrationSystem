package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreditCardController {
    @FXML
    private TextField creditCardNumber;
    @FXML
    private DatePicker expiryDate;

    public void checkCreditCard(ActionEvent event){//need to connect this with the cart so it doesn't accept untill here is true
        long number=Long.valueOf(creditCardNumber.getText());
        GUIUtils utils=new GUIUtils();
        if(utils.isValid(number)){
            ((Node)(event.getSource())).getScene().getWindow().hide();
            //add msg if we have time
        }
    }
}
