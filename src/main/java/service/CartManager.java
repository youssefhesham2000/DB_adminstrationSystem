package service;

import model.Book;
import model.CartItem;
import model.CartItemView;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private DBConnection dbConnection;

    public CartManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean insertCartItem(CartItem cartItem) throws SQLException {
        String query = "INSERT INTO CART(userID, ISBN, quantity) VALUES " +
                "(?,?,?)";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, cartItem.userID);
        statement.setString(2, cartItem.ISBN);
        statement.setInt(3, cartItem.quantity);

        return statement.execute();
    }

    public boolean updateCartItem(CartItem oldCartItem, CartItem newCartItem) throws SQLException {
        String query = "UPDATE CART " +
                "SET quantity=? " +
                "WHERE userID=? AND ISBN=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, newCartItem.quantity);
        statement.setInt(2, oldCartItem.userID);
        statement.setString(3, oldCartItem.ISBN);

        return statement.execute();

    }

    public boolean deleteCartItem(CartItem cartItem) throws SQLException {
        String query = "DELETE FROM CART WHERE userID=? AND ISBN=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, cartItem.userID);
        statement.setString(2, cartItem.ISBN);

        return statement.execute();

    }

    public boolean deleteUserCartItems(User user) throws SQLException {
        String query = "DELETE FROM CART WHERE userID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, user.ID);

        return statement.execute();
    }


    public List<CartItemView> getUserCart(User user) throws SQLException {
        String query =
                "SELECT c.ISBN, b.title, c.quantity, c.quantity*b.sellingPrice as price " +
                "FROM CART c " +
                "JOIN BOOK b ON c.ISBN = b.ISBN " +
                "WHERE userID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, user.ID);
        ResultSet resultSet = statement.executeQuery();

        List<CartItemView> cartItems = new ArrayList<>();
        while (resultSet.next())
            cartItems.add(CartItemView.getCartItemFromResult(resultSet));
        return cartItems;

    }
}
