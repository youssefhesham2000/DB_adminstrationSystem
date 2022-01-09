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
}
