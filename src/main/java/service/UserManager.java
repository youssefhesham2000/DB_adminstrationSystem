package service;

import model.Order;
import model.User;

import java.sql.Connection;

public class UserManager {
    private DBConnection dbConnection;
    public UserManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean insertUser(User user) {

    }

    public boolean updateUser(User oldUser, User newUser) {

    }

    public boolean promoteToManger(User user) {

    }

    public boolean placeOrder(Order order) {

    }

    public boolean confirmOrder(Order order) {

    }

    public boolean deleteUserCart(User user) {

    }
}
