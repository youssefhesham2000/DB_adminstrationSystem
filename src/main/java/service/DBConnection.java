package service;
import java.sql.*;


public class DBConnection {
    Connection connection;
    public DBConnection(String url) throws SQLException {
        connection = DriverManager.getConnection(url,
                "SAMPLE",
                "Root_Password123!");
    }

    public PreparedStatement getPreparedStatement(String SQLQuery) throws SQLException {
        return connection.prepareStatement(SQLQuery);
    }

}
