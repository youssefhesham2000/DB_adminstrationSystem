package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Book;
import model.BookCategory;
import model.User;
import service.UserValidator;

public class ModifyBookPanelController {
    private boolean stateChanged=false;
    WindowChanger changer=new WindowChanger();
    private String oldBookISBN;
    private String oldTitle;
    private String oldPublisherID;
    private String oldPublicationYear;
    private String oldCategory;
    private String oldPrice;
    public ModifyBookPanelController(String ISBN,String Title, String PublisherID,String PublicationYear,String Category,String price){
            this.oldBookISBN=ISBN;
            this.oldTitle=Title;
            this.oldPublisherID=PublisherID;
            this.oldPublicationYear=PublicationYear;
            this.oldCategory=Category;
            this.oldPrice=price;
    }
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
    public void initialize (){
        bookISBN.setText(this.oldBookISBN);
        bookTitle.setText(this.oldTitle); ;
        bookIPublisherID.setText(this.oldPublisherID);
        bookPublicationYear.setText(this.oldPublicationYear);
        bookPrice.setText(this.oldPrice);
        bookCategory.setText(this.oldCategory);
    }
    public void setStateChanged(){
        stateChanged=true;
    }
    public void updateBookInfo(){
        if(stateChanged) {
            UserValidator validator = new UserValidator();
            GUIUtils utils=new GUIUtils();
            if ( validator.isValidBook(bookISBN.getText(),bookTitle.getText(),bookIPublisherID.getText(),bookPublicationYear.getText(),bookCategory.getText(),bookPrice.getText())){
               Book newBook=utils.convertToBook(bookISBN.getText(),bookTitle.getText(),bookIPublisherID.getText(),bookPublicationYear.getText(),bookCategory.getText(),bookPrice.getText());
               Book oldBook=utils.convertToBook(oldBookISBN,oldTitle,oldPublisherID,oldPublicationYear,oldCategory,oldPrice);
               //update book
                //implement me
            }else {
                //show error msg
                changer.createMSGWindow("INvalid Attributes");
            }

        }
    }
}
