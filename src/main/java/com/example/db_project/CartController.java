package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CartController {
    @FXML
    private Text totalPrice;
    @FXML
    public void initializer(){
        //implement me
        //get cart objects and add it and preview it in table (ISBN ,title Quantity ,price) and calculate the total price
        double cartPrice=0;
        totalPrice.setText(Double.toString(cartPrice));
    }
    public void purchase(){
        WindowChanger changer=new WindowChanger();
        CreditCardController controller=new CreditCardController();
        changer.changeWindow("CreditCardPanel.fxml",controller);
        //implement me
        //get items and perform update on DB then get the new cart
    }
}
