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
    WindowChanger changer=new WindowChanger();
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
    @FXML private TableColumn<Book, Void> addToCartButtons;

    @FXML
    public void initialize(){
        GUIUtils utils=new GUIUtils();
        bookISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        bookTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookPublicationYear.setCellValueFactory(new PropertyValueFactory<Book, Integer>("publicationYear"));
//        bookAuthors.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bookCategory.setCellValueFactory(new PropertyValueFactory<Book, BookCategory>("category"));
        bookPrice.setCellValueFactory(new PropertyValueFactory<Book, Double>("sellingPrice"));

        utils.addTOCartButtonToTable("Add To Cart",bookTable,new LoggedInUserController());
        boolean manager=false;
        if(!manager)
            managementButton.setVisible(false);
        else
            utils.addModifyBookButtonToTable("ModifyBook",bookTable,new LoggedInUserController());


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

        CartController controller=new CartController();
        changer.changeWindow("Cart.fxml",controller);
    }
    public void profileButtonIsClicked(){
        UserProfilePreviewController controller=new UserProfilePreviewController();
        changer.changeWindow("UserProfilePreview.fxml",controller);
    }
    public void logOutIsClicked(ActionEvent event){
        //log out of system
        //implement me
        ((Node)(event.getSource())).getScene().getWindow().hide();
        changer.changeWindow("hello-view.fxml",null);

    }
    public void  getIntoManagerOptions(){

    changer.changeWindow("LoggedInManager.fxml",new ManagerController());
    }
    public  void AddToCart(Book selectedBook){
        //implement me
        //add to my cart
        System.out.println(selectedBook.ISBN);
    }
    public void ModifyBook(Book selectedBook){
        System.out.println(selectedBook.ISBN);
        GUIUtils utils =new GUIUtils();
        String results[]=utils.convertBookToStrings(selectedBook);
        changer.changeWindow("ModifyBookPanel.fxml",new ModifyBookPanelController(results[0],results[1],results[2],results[3],results[4],results[5]));

    }
}
