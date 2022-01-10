package service;

import model.Book;
import model.BookCategory;
import model.UserRole;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportManager {

    public static void exportTopTenBooksReport(BookManager bookManager) throws SQLException, IOException {
        ResultSet bookList = bookManager.getTopTenBooks();

        FileWriter fileWriter = new FileWriter("topTen.csv");
        fileWriter.write("ISBN,title,publicationYear,sellingPrice,category,total\n");
        while (bookList.next())
            fileWriter.write(bookList.getString("ISBN") + ","
                    + bookList.getString("title") + ","
                    + bookList.getInt("publicationYear") + ","
                    + bookList.getInt("sellingPrice") + ","
                    + BookCategory.getBookCategoryText(bookList.getInt("category")) + ","
                    + bookList.getDouble("total") + "\n");
        fileWriter.close();
        ProcessBuilder p = new ProcessBuilder("python3", "gen_top_10.py");
        p.start();
    }

    public static void exportTopFiveCustomers(UserManager userManager) throws SQLException, IOException {
        ResultSet resultSet = userManager.genTopFiveCustomers();

        FileWriter fileWriter = new FileWriter("topFiveCustomers.csv");
        fileWriter.write("Email,FirstName,LastName,shippingAddress,phoneNumber,role,total\n");
        while (resultSet.next())
            fileWriter.write(resultSet.getString("email") + ","
                    + resultSet.getString("firstName") + ","
                    + resultSet.getString("lastName") + ","
                    + resultSet.getString("shippingAddress") + ","
                    + resultSet.getString("phoneNumber") + ","
                    + UserRole.getUserRoleString(resultSet.getBoolean("role")) + ","
                    + resultSet.getDouble("total") + "\n");
        fileWriter.close();
        ProcessBuilder p = new ProcessBuilder("python3", "gen_top_5_customers.py");
        p.start();
    }

    public static void exportTotalSales(BookManager bookManager) throws SQLException, IOException {
        double total = bookManager.getTotalSales();

        FileWriter fileWriter = new FileWriter("totalSales.csv");
        fileWriter.write("Total\n" + total + "\n");
        fileWriter.close();
        ProcessBuilder p = new ProcessBuilder("python3", "gen_total_sales.py");
        p.start();
    }


    public static void main(String[] args) throws SQLException, JRException, IOException {
        DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/ORDER_PROCESSING_SYSTEM");
        BookManager bookManager = new BookManager(dbConnection);
        UserManager userManager = new UserManager(dbConnection);
        exportTopTenBooksReport(bookManager);
        exportTopFiveCustomers(userManager);
    }
}
