package service;
import java.sql.*;


public class DBConnection {
    Connection connection;
    public DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://156.194.126.31:3306/ORDER_PROCESSING_SYSTEM",
                "SAMPLE",
                "Root_Password123!");
    }

    public PreparedStatement getPreparedStatement(String SQLQuery) throws SQLException {
        return connection.prepareStatement(SQLQuery);
    }

}
