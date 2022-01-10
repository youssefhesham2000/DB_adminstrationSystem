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
import service.BookManager;

import java.sql.SQLException;
import java.util.List;

public class LoggedInUserController {

    ApplicationLogic applicationLogic = ApplicationLogic.getInstance();
    BookManager bookManager = applicationLogic.bookManager;


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

    int pageNumber = 1;
    @FXML Label pageNumberLabel;
    @FXML Button nextPageButton;
    @FXML Button prevPageButton;

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
