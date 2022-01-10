package com.example.db_project;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class MSGController {
    private String MSG;
    public  MSGController(String MSG){
        this.MSG=MSG;
    }
    @FXML
    private Text MSGText;
    @FXML
    public void initialize(){
        MSGText.setText(MSG);
    }
}
