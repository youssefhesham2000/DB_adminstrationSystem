package model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book implements Serializable {
    public String ISBN;
    public String title;
    public int publisherID;
    public int publicationYear;
    public double sellingPrice;
    public int category;

    public Book(String ISBN, String title, int publisherID, int publicationYear, double sellingPrice, int category) {
        this.ISBN = ISBN;
        this.title = title;
        this.publisherID = publisherID;
        this.publicationYear = publicationYear;
        this.sellingPrice = sellingPrice;
        this.category = category;
    }

    public Book() {}

    public static Book getBookFromResult(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.ISBN = resultSet.getString("ISBN");
        book.title = resultSet.getString("title");
        book.publisherID = resultSet.getInt("publisherID");
        book.publicationYear = resultSet.getInt("publicationYear");
        book.sellingPrice = resultSet.getDouble("sellingPrice");
        book.category = resultSet.getInt("category");
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}