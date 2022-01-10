package com.example.db_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.BookCategory;
import model.Order;
import model.User;
import service.ApplicationLogic;
import service.BookManager;
import service.UserManager;
import service.UserValidator;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class ManagerController {
    WindowChanger changer=new WindowChanger();
    UserManager userManager = ApplicationLogic.getInstance().userManager;
    BookManager bookManager = ApplicationLogic.getInstance().bookManager;
    GUIUtils utils=new GUIUtils();


    @FXML
    private TextField emailToPromote;
    @FXML
    private TextField orderBookISBN;
    @FXML
    private TextField orderQuantity ;
    @FXML
    private DatePicker DeliveryDate;

    @FXML private TableView<Order> orderTable;
    @FXML private TableColumn<Order, Integer> orderIdColumn;
    @FXML private TableColumn<Order, String> ISBNColumn;
    @FXML private TableColumn<Order, Integer> quantityColumn;
    @FXML private TableColumn<Order, Date> requestTimeColumn;
    @FXML private TableColumn<Order, Date> deliveryTimeColumn;


    @FXML
    public void initialize(){
        utils.confirmOrderButtonToTable("Confirm Order",orderTable,this);
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("ID"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("ISBN"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        requestTimeColumn.setCellValueFactory(new PropertyValueFactory<Order, Date>("requestTime"));
        deliveryTimeColumn.setCellValueFactory(new PropertyValueFactory<Order, Date>("deliveryTime"));

        fetchOrders();

    }

    public void fetchOrders(){
        try {
            orderTable.setItems(FXCollections.observableList(userManager.getOrders(1)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
                validator.checkISBN(orderBookISBN.getText()) && DeliveryDate.getValue()!=null ) {
            String BookISBN = orderBookISBN.getText();
            int quantity = Integer.parseInt(orderQuantity.getText());
            LocalDate deliveryDate=DeliveryDate.getValue();


            try {
                if (bookManager.searchBooksByISBN(BookISBN, 1).size() == 0){
                    AlertMessage.showError("Book not found");
                    return;
                }


                userManager.placeOrder(new Order(BookISBN, quantity, Date.valueOf(deliveryDate)));
                AlertMessage.showConfirmation("Order placed");
                fetchOrders();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


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
        try {
            userManager.confirmOrder(confirmedOrder);
            fetchOrders();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
