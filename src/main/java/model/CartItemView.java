package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartItemView {
    public String ISBN;
    public String title;
    public int quantity;

    /**
     * Total price = price of one book * quantity
     */
    public double price;

    public CartItemView() {
    }

    public CartItemView(String ISBN, String title, int quantity, int price) {
        this.ISBN = ISBN;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public static CartItemView getCartItemFromResult(ResultSet resultSet) throws SQLException {
        CartItemView cartItem = new CartItemView();
        cartItem.ISBN = resultSet.getString("ISBN");
        cartItem.title = resultSet.getString("title");
        cartItem.quantity = resultSet.getInt("quantity");
        cartItem.price = resultSet.getDouble("price");

        return cartItem;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
