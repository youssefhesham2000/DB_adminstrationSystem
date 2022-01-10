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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(int publisherID) {
        this.publisherID = publisherID;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}