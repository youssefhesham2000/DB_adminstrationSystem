package com.example.db_project;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.BookCategory;
import model.CartItem;
import service.ApplicationLogic;
import service.BookManager;
import service.CartManager;

import java.sql.SQLException;
import java.util.List;

public class LoggedInUserController {

    ApplicationLogic applicationLogic = ApplicationLogic.getInstance();
    BookManager bookManager = applicationLogic.bookManager;
    CartManager cartManager = applicationLogic.cartManager;


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

    int pageNumber = 1;
    @FXML Label pageNumberLabel;
    @FXML Button nextPageButton;
    @FXML Button prevPageButton;

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
        boolean manager=true;
        if(!manager)
            managementButton.setVisible(false);
        else
            utils.addModifyBookButtonToTable("ModifyBook",bookTable,new LoggedInUserController());

        loadAllBooks();
    }
    @FXML
    public void changeSearchBy(ActionEvent event){
        setPageNumber(1);
        MenuItem source = (MenuItem) event.getSource();
        searchBy=source.getText();
        searchByMenu.setText(searchBy);
    }

    public void search() {
        String searchAtt = searchAttribute.getText();

        try {
            if (searchBy.equals("")){
                loadBooksTable(bookManager.getAllBooks(pageNumber));
                return;
            }

            List<Book> books;


            switch (searchBy) {
                case "Title":
                    books = bookManager.searchBooksByTitle(searchAtt, pageNumber);
                    break;
                case "ISBN":
                    books = bookManager.searchBooksByISBN(searchAtt, pageNumber);
                    break;
                case "Author":
                    books = bookManager.searchBooksByAuthor(searchAtt, pageNumber);
                    break;
                case "Publication year":
                    books = bookManager.searchBooksByPublicationYear(searchAtt, pageNumber);
                    break;
                default:
                    books = bookManager.getAllBooks(pageNumber);
            }

            loadBooksTable(books);


        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void cartButtonIsClicked(){
        changer.changeWindow("Cart.fxml",null);
    }

    public void profileButtonIsClicked(){
        WindowChanger changer =new WindowChanger();
        changer.changeWindow("UserProfilePreview.fxml",null);
    }

    public void logOutIsClicked(ActionEvent event){
        //log out of system
        //implement me
        ((Node)(event.getSource())).getScene().getWindow().hide();
        changer.changeWindow("hello-view.fxml",null);

    }

    @FXML public void  getIntoManagerOptions(){
        WindowChanger changer=new WindowChanger();
        changer.changeWindow("LoggedInManager.fxml",null);
    }

    public  void AddToCart(Book selectedBook){
        try {
            cartManager.insertCartItem(new CartItem(1, selectedBook.ISBN, 1));
            AlertMessage.showConfirmation("\"" + selectedBook.title + "\" added successfully to cart");

        } catch (SQLException throwables) {
            AlertMessage.showError("Item already exists in cart");
        }
    }

    public void ModifyBook(Book selectedBook){
        System.out.println(selectedBook.ISBN);
        GUIUtils utils =new GUIUtils();
        String results[]=utils.convertBookToStrings(selectedBook);
        changer.changeWindow("ModifyBookPanel.fxml",new ModifyBookPanelController(results[0],results[1],results[2],results[3],results[4],results[5]));

    }


    @FXML public void searchClicked(ActionEvent event){
        setPageNumber(1);
        search();
    }

    @FXML public void nextPageClicked(ActionEvent event){
        setPageNumber(pageNumber+1);
        search();
    }

    @FXML public void prevPageClicked(ActionEvent event){
        if (pageNumber <= 1)  return;

        setPageNumber(pageNumber-1);
        search();
    }


    void loadAllBooks(){
        try {
            loadBooksTable(bookManager.getAllBooks(pageNumber));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Loads the table view with the given books
     * @param books
     */
    void loadBooksTable(List<Book> books){
        bookTable.setItems(FXCollections.observableList(books));
    }

    void setPageNumber(int pageNumber){
        this.pageNumber = pageNumber;
        pageNumberLabel.setText(String.valueOf(pageNumber));
    }


}
