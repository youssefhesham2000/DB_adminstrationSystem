package service;

import model.BookCategory;
import model.Order;
import model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserManager {
    private DBConnection dbConnection;
    public UserManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean insertUser(User user, String password) throws SQLException {
        String query = "INSERT INTO USER(firstName, lastName, email, shippingAddress, phoneNumber) VALUES " +
                "(?,?,?,?,?,?)";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, user.firstName);
        statement.setString(2, user.lastName);
        statement.setString(3, user.email);
        statement.setString(4, user.shippingAddress);
        statement.setString(5, user.phoneNumber);
        statement.setString(6, password);

        return statement.execute();
    }

    public boolean updateUser(User oldUser, User newUser) throws SQLException {
        String query = "UPDATE USER " +
                "SET firstName=?, lastName=?, email=?, shippingAddress=?, phoneNumber=? " +
                "WHERE ID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, newUser.firstName);
        statement.setString(2, newUser.lastName);
        statement.setString(3, newUser.email);
        statement.setString(4, newUser.shippingAddress);
        statement.setString(5, newUser.phoneNumber);
        statement.setInt(6, 1);

        return statement.execute();
    }


    public boolean updatePassword(User user, String newPassword) throws SQLException {
        String query = "UPDATE USER " +
                "SET password=? " +
                "WHERE ID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, newPassword);
        statement.setInt(2, user.ID);

        return statement.execute();
    }

    public boolean promoteToManger(User user) {
        return false;
    }

    public boolean placeOrder(Order order) throws SQLException {
        String query = "INSERT INTO LIBRARY_ORDER_DETAILS(ID, ISBN, quantity, requestTime, deliveryTime) VALUES " +
                "(?,?,?,?,?)";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, order.ID);
        statement.setString(2, order.ISBN);
        statement.setInt(3, order.quantity);
        statement.setDate(4, order.requestTime);
        statement.setDate(5, order.deliveryTime);

        return statement.execute();
    }

    public boolean confirmOrder(Order order) throws SQLException {
        String query = "DELETE FROM LIBRARY_ORDER_DETAILS WHERE ID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, order.ID);      // TODO: write delete trigger

        return statement.execute();

    }
}
