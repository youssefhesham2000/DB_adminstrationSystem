package service;

import model.Book;

import java.util.HashSet;
import java.util.stream.Stream;

public class UserValidator {
    public boolean checkFieldsCorrectness(String email,String password,String number,String LName,String FName,String Address){
        return  number.matches("[0-9]+") && FName.matches("[a-zA-Z]+")&& email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")&&
                LName.matches("[a-zA-Z]+")&& Address.matches("[a-zA-Z0-9]*")&&password.length()>8;
    }
    public boolean checkNumeric(String toCheck){
        return toCheck.matches("[0-9]+");
    }
    public boolean checkNumericOrLetters(String toCheck){
        return toCheck.matches("[a-zA-Z0-9\\s]*");
    }
    public boolean checkISBN(String toCheck){
        return toCheck.matches("[a-zA-Z0-9\\-]*");
    }
    public boolean checkLetters(String toCheck){
        return toCheck.matches("[a-zA-Z]+");
    }
    public boolean isValidBook(String ISBN,String Title, String PublisherID,String PublicationYear,String Category,String price){
        return checkISBN(ISBN)
                && checkNumericOrLetters(Title)
                && checkNumeric(PublisherID)
                && validateYear(PublicationYear)
                && ValidateCategory(Category)
                && isDouble(price);
    }
    public boolean validateYear(String year){
        return year.matches("[0-9]+")&& Integer.parseInt(year)>0;
    }
    public boolean ValidateCategory(String category){
       return Stream.of("Science", "Art", "Religion", "History","Geography").anyMatch(category::equalsIgnoreCase);
    }

    public boolean isDouble(String s){
        try {
            Double d = Double.parseDouble(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
