package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Book;

public class ManagerBookLIstController {
    private String searchBy="";
    @FXML
    private MenuButton searchByMenu;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TextField searchAttribute;
    @FXML
    public void initializer(){
        //implement me
        //get all books
        GUIUtils utils =new GUIUtils();
        //utils.addModifyBookButtonToTable("Modify Book",bookTable,new ManagerBookLIstController());
    }
    public void changeSearchBy(ActionEvent event){
        MenuItem source = (MenuItem) event.getSource();
        searchBy=source.getText();
        searchByMenu.setText(searchBy);
    }
    public void  Search(){
        String searchAtt=searchAttribute.getText();
        if(searchAtt!=""&&searchBy!=""){
            //call search function
            //implement me
        }
    }
    public void ModifyBook(Book selectedBook){
        //implement me
        System.out.println(selectedBook.ISBN);
    }
}
