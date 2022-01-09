package service;

import model.CartItem;
import model.User;

import java.util.List;

public class CartManager {
    private DBConnection dbConnection;

    public CartManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean insertCartItem(CartItem cartItem) {

    }

    public boolean updateCartItem(CartItem oldCartItem, CartItem newCartItem) {

    }

    public boolean deleteCartItem(CartItem cartItem) {

    }

    List<CartItem> getUserCart(User user) {

    }

}
