package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import model.Book;

public class LoggedInUserController {
    private String searchBy="";
    @FXML
    private MenuButton searchByMenu;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TextField searchAttribute;
    @FXML
    private Button managementButton;
    @FXML
    public void initialize(){
    //get user type and put in manager boolean var
        boolean manager=true;
        if(!manager)
            managementButton.setVisible(false);

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
        ((Node)(event.getSource())).getScene().getWindow().hide();
        WindowChanger changer=new WindowChanger();
        changer.changeWindow("hello-view.fxml",null);

    }
    public void  getIntoManagerOptions(){
    WindowChanger changer=new WindowChanger();
    changer.changeWindow("LoggedInManager.fxml",new ManagerController());
    }
}
