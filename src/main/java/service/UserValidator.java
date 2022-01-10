package service;

public class UserValidator {
    public boolean checkFieldsCorrectness(String email,String password,String number,String LName,String FName,String Address){
        return  number.matches("[0-9]+") && FName.matches("[a-zA-Z]+")&& email.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")&&
                LName.matches("[a-zA-Z]+")&& Address.matches("[a-zA-Z0-9]*")&&password.length()>8;
    }
}
