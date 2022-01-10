package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.UserValidator;

public class AddBookController {
   UserValidator validator=new UserValidator();
    @FXML
    private TextField bookISBN;
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
            //add book to DB if book already exisit  change exisited to true
            if(!existed)
                changer.createMSGWindow("Added Sucessfully");
            else
                changer.createMSGWindow("Book already exist");
        }else {
            changer.createMSGWindow("Invalid Attributes");
        }
    }
}
