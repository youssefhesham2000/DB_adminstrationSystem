package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.UserValidator;

import java.time.LocalDate;

public class ManagerController {
    WindowChanger changer=new WindowChanger();
    @FXML
    private TextField emailToPromote;
    @FXML
    private TextField orderBookISBN;
    @FXML
    private TextField orderQuantity ;
    @FXML
    private DatePicker DeliveryDate;

    @FXML
    public void promote(){
        String email=emailToPromote.getText();
        boolean found=true;
        //search for the email if found set found to true and promote it
        String msg =found?  "promoted" : "email not found";

        changer.createMSGWindow(msg);
    }
    public void addBookIsClicked(){
        changer.changeWindow("AddBookPanel.fxml",new AddBookController());

    }
    public void placeOrderIsClicked(){
        UserValidator validator=new UserValidator();
        if(validator.checkNumeric(orderQuantity.getText())&&
                validator.checkNumericOrLetters(orderBookISBN.getText()) && DeliveryDate.getValue()!=null ) {
            String BookISBN = orderBookISBN.getText();
            int quantity = Integer.parseInt(orderQuantity.getText());
            LocalDate deliveryDate=DeliveryDate.getValue();
            boolean found=true;
            // add TO DB

            //if ISBN not found set bool found to false
            if(!found)
                changer.createMSGWindow("book not found");
        }
        else{
            changer.createMSGWindow("wrong attributes");
        }
    }
    public void enterModifyList(){
        changer.changeWindow("ManagerBookList.fxml",new ManagerBookLIstController());
    }
}
