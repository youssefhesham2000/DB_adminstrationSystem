package service;

import model.BookCategory;
import model.Order;
import model.User;
import model.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private DBConnection dbConnection;
    public UserManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean insertUser(User user, String password) throws SQLException {
        String query = "INSERT INTO USER(firstName, lastName, email, shippingAddress, phoneNumber, role) VALUES " +
                "(?,?,?,?,?,?)";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, user.firstName);
        statement.setString(2, user.lastName);
        statement.setString(3, user.email);
        statement.setString(4, user.shippingAddress);
        statement.setString(5, user.phoneNumber);
        statement.setString(6, password);
        statement.setBoolean(7, UserRole.getUserRoleIndex(user.role));

        return statement.execute();
    }

    public boolean updateUser(User oldUser, User newUser) throws SQLException {
        String query = "UPDATE USER " +
                "SET firstName=?, lastName=?, email=?, shippingAddress=?, phoneNumber=?, role=? " +
                "WHERE ID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, newUser.firstName);
        statement.setString(2, newUser.lastName);
        statement.setString(3, newUser.email);
        statement.setString(4, newUser.shippingAddress);
        statement.setString(5, newUser.phoneNumber);
        statement.setBoolean(6, UserRole.getUserRoleIndex(newUser.role));
        statement.setInt(7, oldUser.ID);


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

    public boolean promoteToManager(User user) throws SQLException {
        String query = "UPDATE USER SET role=1 WHERE ID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, user.ID);

        return statement.execute();
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

    public List<Order> getOrders(int pageNumber) throws SQLException {
        String query = "SELECT * FROM LIBRARY_ORDER_DETAILS LIMIT ?,10";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, (pageNumber - 1) * 10);

        ResultSet resultSet = statement.executeQuery();
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next())
            orderList.add(Order.getOrderFromResult(resultSet));
        return orderList;
    }

    public boolean confirmOrder(Order order) throws SQLException {
        String query = "DELETE FROM LIBRARY_ORDER_DETAILS WHERE ID=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, order.ID);      // TODO: write delete trigger

        return statement.execute();

    }

    public User login(String email, String password) throws SQLException {
        String query = "SELECT * FROM USER WHERE email=? AND password=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        User user = null;
        if(resultSet.next())
            user = User.getUserFromResult(resultSet, true);

        ApplicationLogic.getInstance().loggedInUser = user;
        return user;
    }

    public boolean buyCart(User user) throws SQLException {
        String callableQuery = "{ CALL purchaseCart(?) }";
        CallableStatement statement = dbConnection.getCallableStatement(callableQuery);
        statement.setInt(1, user.ID);

        return statement.execute();
    }
}
