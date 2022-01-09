package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
    public String ISBN;
    public String title;
    public int publisherID;
    public int publicationYear;
    public double sellingPrice;
    public BookCategory category;

    public static Book getBookFromResult(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.ISBN = resultSet.getString("ISBN");
        book.title = resultSet.getString("title");
        book.publisherID = resultSet.getInt("publisherID");
        book.publicationYear = resultSet.getInt("publicationYear");
        book.sellingPrice = resultSet.getDouble("sellingPrice");
        book.category = BookCategory.getBookCategory(resultSet.getInt("category"));
        return book;
    }
}