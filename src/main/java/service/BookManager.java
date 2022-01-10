package service;

import model.Book;
import model.BookCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private final DBConnection dbConnection;

    public BookManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean insertBook(Book book) throws SQLException { // TODO: AUTHOR HANDLING
        String query = "INSERT INTO BOOK(ISBN, title, publisherID, publicationYear, sellingPrice, category) VALUES " +
                "(?,?,?,?,?,?)";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, book.ISBN);
        statement.setString(2, book.title);
        statement.setInt(3, book.publisherID);
        statement.setInt(4, book.publicationYear);
        statement.setDouble(5, book.sellingPrice);
        statement.setInt(6, book.category);

        return statement.execute();
    }

    public boolean updateBook(Book oldBook, Book newBook) throws SQLException {
        String query = "UPDATE BOOK " +
                "SET title=?, publisherID=?, publicationYear=?, sellingPrice=?, category=? " +
                "WHERE ISBN=?";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, newBook.title);
        statement.setInt(2, newBook.publisherID);
        statement.setInt(3, newBook.publicationYear);
        statement.setDouble(4, newBook.sellingPrice);
        statement.setInt(5, newBook.category);
        statement.setString(6, oldBook.ISBN);

        return statement.execute();
    }

    public List<Book> getAllBooks(int pageNumber) throws SQLException {
        String query = "SELECT * FROM BOOK LIMIT ?,10";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setInt(1, (pageNumber-1)*10);
        ResultSet resultSet = statement.executeQuery();
        List<Book> bookList = new ArrayList<>();

        while (resultSet.next())
            bookList.add(Book.getBookFromResult(resultSet));
        return bookList;
    }

    public List<Book> searchBooksByAuthor(String authorName, int pageNumber) throws SQLException {
        String query = "SELECT B.* FROM BOOK B " +
                "INNER JOIN BOOK_AUTHORS A ON B.ISBN=A.ISBN " +
                "WHERE A.name=(SELECT ID FROM AUTHORS WHERE name LIKE ?)" +
                "LIMIT ?,10";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, authorName + "%");
        statement.setInt(2, (pageNumber-1)*10);
        ResultSet resultSet = statement.executeQuery();

        List<Book> bookList = new ArrayList<>();
        while (resultSet.next())
            bookList.add(Book.getBookFromResult(resultSet));
        return bookList;
    }

    public List<Book> searchBooksByISBN(String ISBN, int pageNumber) throws SQLException {
        String query = "SELECT * FROM BOOK WHERE ISBN LIKE ? LIMIT ?,10";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, ISBN + "%");
        statement.setInt(2, (pageNumber-1)*10);
        ResultSet resultSet = statement.executeQuery();

        List<Book> bookList = new ArrayList<>();
        while (resultSet.next())
            bookList.add(Book.getBookFromResult(resultSet));
        return bookList;
    }

    public List<Book> searchBooksByTitle(String title, int pageNumber) throws SQLException {
        String query = "SELECT * FROM BOOK WHERE title LIKE ? LIMIT ?,10";

        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, title + "%");
        statement.setInt(2, (pageNumber-1)*10);
        ResultSet resultSet = statement.executeQuery();

        List<Book> bookList = new ArrayList<>();
        while (resultSet.next())
            bookList.add(Book.getBookFromResult(resultSet));
        return bookList;
    }

    public List<Book> searchBooksByPublicationYear(String year, int pageNumber) throws SQLException {
        String query = "SELECT * FROM BOOK WHERE publicationYear = ? LIMIT ?,10";

        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        statement.setString(1, year);
        statement.setInt(2, (pageNumber-1)*10);
        ResultSet resultSet = statement.executeQuery();

        List<Book> bookList = new ArrayList<>();
        while (resultSet.next())
            bookList.add(Book.getBookFromResult(resultSet));
        return bookList;
    }

    public ResultSet getTopTenBooks() throws SQLException {
        String query = "SELECT * FROM topTenBooks";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    public double getTotalSales() throws SQLException {
        String query = "SELECT SUM(totalPrice) AS total FROM BOOK_SALES";
        PreparedStatement statement = dbConnection.getPreparedStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next())
            return resultSet.getDouble("total");
        return 0;
    }
}
