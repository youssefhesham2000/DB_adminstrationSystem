package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class CartController {
    @FXML
    private Text totalPrice;
    @FXML
    public void initializer(){
        //get cart objects and add it and preview it in table (ISBN ,title Quantity ,price)
        totalPrice.setText("1800");
    }
    public void purchase(){
        WindowChanger changer=new WindowChanger();
        CreditCardController controller=new CreditCardController();
        changer.changeWindow("CreditCardPanel.fxml",controller);

        //get items and perform update on DB then get the new cart
    }
}
