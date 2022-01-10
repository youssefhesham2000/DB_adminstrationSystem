package com.example.db_project;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.*;
import service.ApplicationLogic;
import service.CartManager;

import javax.crypto.spec.GCMParameterSpec;
import java.sql.SQLException;

public class CartController {
    User user = ApplicationLogic.getInstance().loggedInUser;
    CartManager cartManager = ApplicationLogic.getInstance().cartManager;
    GUIUtils utils=new GUIUtils();
    @FXML
    private Text totalPrice;

    @FXML TableView<CartItemView> cartTable;
    @FXML private TableColumn<CartItemView, String> ISBNColumn;
    @FXML private TableColumn<CartItemView, String> titleColumn;
    @FXML private TableColumn<CartItemView, Integer> quantityColumn;
    @FXML private TableColumn<CartItemView, Double> priceColumn;



    @FXML
    public void initialize(){
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, String>("ISBN"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, String>("title"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, Integer>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, Double>("price"));


        try {
            cartTable.setItems(FXCollections.observableList(cartManager.getUserCart(user)));
        } catch (SQLException throwable) {
            AlertMessage.showError("Failed to get user cart");
        }

        utils.removeCartItemViewToTable("remove Item",cartTable,this);
        double cartPrice=0;
        totalPrice.setText(Double.toString(cartPrice));
    }
    public void purchase(ActionEvent event){
        WindowChanger changer=new WindowChanger();
        CreditCardController controller=new CreditCardController();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        changer.changeWindow("CreditCardPanel.fxml",controller);


    }
    public void removeFromCart(CartItemView Item){
        System.out.println(Item.ISBN);
        //remove from DB
        cartTable.getItems().clear();
        //get all cart objects and load it to table again
        utils.removeCartItemViewToTable("remove Item",cartTable,this);
    }
    public void increaseQuantity(CartItemView selectedItem){
        System.out.println(selectedItem.ISBN);
        //update DB
        cartTable.getItems().clear();
        //get all cart objects and load it to table again
        //utils.removeCartItemToTable("remove Item",cartTable,this);
    }
}
