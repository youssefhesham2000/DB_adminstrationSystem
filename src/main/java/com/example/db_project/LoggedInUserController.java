package com.example.db_project;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.BookCategory;
import service.ApplicationLogic;

import java.sql.SQLException;
import java.util.List;

public class LoggedInUserController {

    ApplicationLogic applicationLogic = ApplicationLogic.getInstance();


    private String searchBy="";
    @FXML private MenuButton searchByMenu;
    @FXML private TextField searchAttribute;
    @FXML private Button managementButton;



    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, String> bookISBN;
    @FXML private TableColumn<Book, String> bookTitle;
    @FXML private TableColumn<Book, Integer> bookPublicationYear;
    @FXML private TableColumn<Book, String> bookAuthors;
    @FXML private TableColumn<Book, BookCategory> bookCategory;
    @FXML private TableColumn<Book, Double> bookPrice;
    @FXML private TableColumn<Book, String> addToCartButtons;

    @FXML
    public void initialize(){

        bookISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookPublicationYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("publicationYear"));
//        bookAuthors.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookCategory.setCellValueFactory(new PropertyValueFactory<Book, BookCategory>("category"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("sellingPrice"));

        boolean manager=true;
        if(!manager)
            managementButton.setVisible(false);


        try {
            List<Book> books = applicationLogic.bookManager.getAllBooks(0);
            System.out.println(books.size());
            bookTable.setItems(FXCollections.observableList(books));



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @FXML
    public void changeSearchBy(ActionEvent event){
        MenuItem source = (MenuItem) event.getSource();
        searchBy=source.getText();
        searchByMenu.setText(searchBy);
    }
    public void  Search(){
        String searchAtt=searchAttribute.getText();
        if(searchAtt!=""&&searchBy!=""){
            //implement me
            //call search function
        }
    }
    public void cartButtonIsClicked(){
        WindowChanger changer=new WindowChanger();
        CartController controller=new CartController();
        changer.changeWindow("Cart.fxml",controller);
    }
    public void profileButtonIsClicked(){
        WindowChanger changer =new WindowChanger();
        UserProfilePreviewController controller=new UserProfilePreviewController();
        changer.changeWindow("UserProfilePreview.fxml",controller);
    }
    public void logOutIsClicked(ActionEvent event){
        //log out of system
        //implement me
        ((Node)(event.getSource())).getScene().getWindow().hide();
        WindowChanger changer=new WindowChanger();
        changer.changeWindow("hello-view.fxml",null);

    }
    public void  getIntoManagerOptions(){
    WindowChanger changer=new WindowChanger();
    changer.changeWindow("LoggedInManager.fxml",new ManagerController());
    }
}
