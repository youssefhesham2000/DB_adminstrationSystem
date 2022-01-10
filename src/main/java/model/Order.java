package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    public int ID;
    public String ISBN;
    public int quantity;
    public Date requestTime;
    public Date deliveryTime;

    public static Order getOrderFromResult(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.ID = resultSet.getInt("ID");
        order.ISBN = resultSet.getString("ISBN");
        order.quantity = resultSet.getInt("quantity");
        order.requestTime = resultSet.getDate("requestTime");
        order.deliveryTime = resultSet.getDate("deliveryTime");

        return order;
    }

    public Order() {
    }

    public Order(String ISBN, int quantity, Date requestTime) {
        this.ISBN = ISBN;
        this.quantity = quantity;
        this.requestTime = requestTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
