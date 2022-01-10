package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesReportingManager {
    private final DBConnection dbConnection;

    public SalesReportingManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public double totalSales() throws SQLException {
        String query = "SELECT SUM(totalPrice) AS total from BOOK_SALES";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return resultSet.getDouble("total");
        return 0;
    }
}
