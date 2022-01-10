package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model.CartItem;

import javax.crypto.spec.GCMParameterSpec;

public class CartController {
    GUIUtils utils=new GUIUtils();
    @FXML
    private Text totalPrice;
    @FXML
    TableView<CartItem> cartTable;

    @FXML
    public void initialize(){
        //implement me
        //get cart objects and add it and preview it in table (ISBN ,title Quantity ,price) and calculate the total price

        utils.removeCartItemToTable("remove Item",cartTable,new CartController());
        double cartPrice=0;
        totalPrice.setText(Double.toString(cartPrice));
    }
    public void purchase(ActionEvent event){
        WindowChanger changer=new WindowChanger();
        CreditCardController controller=new CreditCardController();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        changer.changeWindow("CreditCardPanel.fxml",controller);


    }
    public void removeFromCart(CartItem Item){
        System.out.println(Item.ISBN);
        //remove from DB
        cartTable.getItems().clear();
        //get all cart objects and load it to table again
        utils.removeCartItemToTable("remove Item",cartTable,new CartController());
    }
    public void increaseQuantity(CartItem selectedItem){
        System.out.println(selectedItem.ISBN);
        //update DB
        cartTable.getItems().clear();
        //get all cart objects and load it to table again
        utils.removeCartItemToTable("remove Item",cartTable,new CartController());
    }
}
