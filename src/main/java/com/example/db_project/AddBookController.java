package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Book;
import service.ApplicationLogic;
import service.BookManager;
import service.UserValidator;

import java.sql.SQLException;

public class AddBookController {

    BookManager bookManager = ApplicationLogic.getInstance().bookManager;
   UserValidator validator=new UserValidator();
    @FXML
    private TextField bookISBN;
    @FXML
    private TextField threshold;
    @FXML
    private TextField bookTitle ;
    @FXML
    private TextField bookIPublisherID;
    @FXML
    private TextField bookPublicationYear;
    @FXML
    private TextField bookPrice;
    @FXML
    private TextField bookCategory;

    @FXML
    public void addBook(){
        WindowChanger changer=new WindowChanger();
        if(validator.isValidBook(bookISBN.getText(),bookTitle.getText(),bookIPublisherID.getText(),bookPublicationYear.getText(),bookCategory.getText(),bookPrice.getText())){
            boolean existed=false;
            String ISBN=bookISBN.getText();
            String Title=bookTitle.getText();
            String PublisherId=bookIPublisherID.getText();
            String PublicationYear=bookPublicationYear.getText();
            String Category=bookCategory.getText();
            String Price = bookPrice.getText();
            String Threshold=threshold.getText();
            GUIUtils guiUtils = new GUIUtils();

            try {
                bookManager.insertBook(guiUtils.convertToBook(ISBN, Title, PublisherId, PublicationYear, Category, Price));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if(!existed)
                changer.createMSGWindow("Added Sucessfully");
            else
                changer.createMSGWindow("Book already exist");
        }else {
            changer.createMSGWindow("Invalid Attributes");
        }
    }
}
