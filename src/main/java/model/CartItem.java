package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItem {
    public int userID;
    public String ISBN;
    public int quantity;

    public CartItem() {
    }

    public CartItem(int userID, String ISBN, int quantity) {
        this.userID = userID;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    public static CartItem getCartItemFromResult(ResultSet resultSet) throws SQLException {
        CartItem cartItem = new CartItem();
        cartItem.userID = resultSet.getInt("userID");
        cartItem.ISBN = resultSet.getString("ISBN");
        cartItem.quantity = resultSet.getInt("quantity");

        return cartItem;
    }

}
