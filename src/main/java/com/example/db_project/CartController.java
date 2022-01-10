package com.example.db_project;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.stream.Collectors;

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


    ObservableList<CartItemView> cartItems;



    @FXML
    public void initialize(){
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, String>("ISBN"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, String>("title"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, Integer>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<CartItemView, Double>("price"));


        try {

            cartItems = FXCollections.observableList(cartManager.getUserCart(user));
            cartItems.addListener(
                    (ListChangeListener<CartItemView>) change -> {
                        calculateTotalPrice();
                    });

            cartTable.setItems(cartItems);
            calculateTotalPrice();



        } catch (SQLException throwable) {
            AlertMessage.showError("Failed to get user cart");
        }

        utils.removeCartItemViewToTable("remove Item",cartTable,this);
        utils.insertIncreaseQuantityButtonToTable("Increase quantity", cartTable, this);

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
        cartItems.remove(Item);

        try {
            cartManager.deleteCartItem(new CartItem(user.ID, Item.ISBN, Item.quantity));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void increaseQuantity(CartItemView selectedItem){
        System.out.println(selectedItem.ISBN);
        int index = cartTable.getItems().indexOf(selectedItem);
        int oldQuantity = cartTable.getItems().get(index).quantity;

        selectedItem.quantity++;
        cartItems.set(index, selectedItem);

        try {
            cartManager.updateCartItem(
                    new CartItem(user.ID, selectedItem.ISBN, oldQuantity),
                    new CartItem(user.ID, selectedItem.ISBN, oldQuantity+1)
                    );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void calculateTotalPrice(){
        double p = cartItems.
                stream().
                map(c -> c.price * c.quantity).mapToDouble(Double::doubleValue).sum();
        totalPrice.setText(String.valueOf(p));

    }


}
