package com.example.db_project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowChanger {
    public void changeWindow(String FXMLFile,Object controller){//controller can be null if we are changing to log in page
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(FXMLFile));
            if(controller!=null)
                fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createMSGWindow(String MSG){//controller can be null if we are changing to log in page
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MSG.fxml"));
            fxmlLoader.setController(new MSGController(MSG));
            Scene scene = new Scene(fxmlLoader.load(), 300, 200);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
