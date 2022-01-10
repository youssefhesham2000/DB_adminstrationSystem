package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Order;
import model.User;
import service.ApplicationLogic;
import service.UserManager;
import service.UserValidator;

import java.sql.SQLException;
import java.time.LocalDate;

public class ManagerController {
    WindowChanger changer=new WindowChanger();
    UserManager userManager = ApplicationLogic.getInstance().userManager;
    GUIUtils utils=new GUIUtils();
    @FXML
    private TextField emailToPromote;
    @FXML
    private TextField orderBookISBN;
    @FXML
    private TextField orderQuantity ;
    @FXML
    private DatePicker DeliveryDate;
    @FXML
    private TableView<Order> orderTable;
    @FXML
    public void initialize(){
        utils.confirmOrderButtonToTable("Confirm Order",orderTable,new ManagerController());
    }

    @FXML
    public void promote(){
        String email=emailToPromote.getText();

        try {
            boolean found = userManager.promoteToManager(email);

            if (!found)
                AlertMessage.showError("Email not found");
            else AlertMessage.showConfirmation(email + " promoted");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
            // add order TO DB
            //implement me
            //if ISBN not found set bool found to false
            if(!found)
                changer.createMSGWindow("book not found");
            else
                changer.createMSGWindow("Order is Placed");
        }
        else{
            changer.createMSGWindow("wrong attributes");
        }
    }
    /*public void enterModifyList(){
        changer.changeWindow("ManagerBookList.fxml",new ManagerBookLIstController());
    }*/
    public void viewReportsIsClicked(){
        //handle jasper  report generator code here
        //implement me
    }
    public void confirmOrder(Order confirmedOrder){
        System.out.println(confirmedOrder.ID);
        //confirm order to DB
        orderTable.getItems().clear();
        //and re load orders data

        utils.confirmOrderButtonToTable("Confirm Order",orderTable,this);


    }
}
