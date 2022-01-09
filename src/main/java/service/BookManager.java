package service;

import model.Book;

import java.sql.Connection;
import java.util.List;

public class BookManager {
    private DBConnection dbConnection;

    public BookManager(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public boolean insertBook(Book book) {

    }

    public boolean updateBook(Book oldBook, Book newBook) {

    }

    public List<Book> searchBy(String fieldName) {

    }


}
