package service;
import java.sql.*;


public class DBConnection {
    Connection connection;
    DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SAMPLE18010078_18015026");
    }


}
