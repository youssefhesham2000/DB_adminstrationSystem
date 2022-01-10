package com.example.db_project;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Book;
import model.BookCategory;
import model.CartItemView;
import model.Order;

import java.util.Locale;

public class GUIUtils {
    public boolean purchased=false;
    public Book convertToBook(String ISBN, String Title, String PublisherID, String PublicationYear, String Category, String price){
    Book newBook=new Book();
        newBook.ISBN=ISBN;
        newBook.title=Title;
        newBook.publisherID=Integer.parseInt(PublisherID);
        newBook.publicationYear=Integer.parseInt(PublicationYear);
        newBook.sellingPrice=Double.parseDouble(price);
        System.out.println(Category);
//        sout(BookCategory.valueOf(Category.toUpperCase()));
        newBook.category= BookCategory.valueOf(Category.toUpperCase());
        return newBook;
    }
    public String[]convertBookToStrings(Book book){
        String newBook[]=new String[6];
        newBook[0]=book.ISBN;
        newBook[1]=book.title;
        newBook[2]=Integer.toString(book.publisherID);
        newBook[3]=Integer.toString(book.publicationYear);
        newBook[4]=Double.toString(book.sellingPrice);
        //newBook.category=new BookCategory(bookCategory.getText()); category is not yet determined how to handle it
        return newBook;
    }

    public void addTOCartButtonToTable( String textToDisplay, TableView<Book> table,LoggedInUserController Controller) {
        TableColumn<Book, String> colBtn = new TableColumn("Button Column");
        Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFactory
                = //
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Book, String> param) {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {

                            final Button btn = new Button(textToDisplay);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Book book = getTableView().getItems().get(getIndex());
                                        Controller.AddToCart(book);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);

    }


    public void removeCartItemViewToTable(String textToDisplay, TableView<CartItemView> table, CartController Controller) {
        TableColumn<CartItemView, String> colBtn = new TableColumn("Button Column");
        Callback<TableColumn<CartItemView, String>, TableCell<CartItemView, String>> cellFactory
                = //
                new Callback<TableColumn<CartItemView, String>, TableCell<CartItemView, String>>() {
                    @Override
                    public TableCell call(final TableColumn<CartItemView, String> param) {
                        final TableCell<CartItemView, String> cell = new TableCell<CartItemView, String>() {

                            final Button btn = new Button(textToDisplay);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        CartItemView Item = getTableView().getItems().get(getIndex());
                                        Controller.removeFromCart(Item);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);

    }

    public void insertIncreaseQuantityButtonToTable(String textToDisplay, TableView<CartItemView> table, CartController Controller) {
        TableColumn<CartItemView, String> colBtn = new TableColumn("increase Button Column");
        Callback<TableColumn<CartItemView, String>, TableCell<CartItemView, String>> cellFactory
                = //
                new Callback<TableColumn<CartItemView, String>, TableCell<CartItemView, String>>() {
                    @Override
                    public TableCell call(final TableColumn<CartItemView, String> param) {
                        final TableCell<CartItemView, String> cell = new TableCell<CartItemView, String>() {

                            final Button btn = new Button(textToDisplay);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        CartItemView Item = getTableView().getItems().get(getIndex());
                                        Controller.increaseQuantity(Item);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);

    }


    public void confirmOrderButtonToTable(String textToDisplay, TableView<Order> table, ManagerController Controller) {
        TableColumn<Order, String> colBtn = new TableColumn("Button Column");
        Callback<TableColumn<Order, String>, TableCell<Order, String>> cellFactory
                = //
                new Callback<TableColumn<Order, String>, TableCell<Order, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Order, String> param) {
                        final TableCell<Order, String> cell = new TableCell<Order, String>() {

                            final Button btn = new Button(textToDisplay);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Order order= getTableView().getItems().get(getIndex());
                                        Controller.confirmOrder(order);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);

    }
    public void addModifyBookButtonToTable( String textToDisplay, TableView<Book> table,LoggedInUserController Controller) {
        TableColumn<Book, String> colBtn = new TableColumn("Button Column");
        Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFactory
                = //
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Book, String> param) {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {

                            final Button btn = new Button(textToDisplay);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Book book = getTableView().getItems().get(getIndex());
                                       Controller.ModifyBook(book);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);

    }
    // Return true if the card number is valid
    public  boolean isValid(long number)
    {
        return (getSize(number) >= 13 &&
                getSize(number) <= 16) &&
                (prefixMatched(number, 4) ||
                        prefixMatched(number, 5) ||
                        prefixMatched(number, 37) ||
                        prefixMatched(number, 6)) &&
                ((sumOfDoubleEvenPlace(number) +
                        sumOfOddPlace(number)) % 10 == 0);
    }

    // Get the result from Step 2
    private int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    private int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    private int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    private boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in d
    private int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    private long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }
}
