package com.example.javafxco1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 750);
        //Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //Scene scene = new Scene(root, 1100, 750);
        stage.setTitle("Wee Woo Wee WOo1!");
        stage.setScene(scene);
        scene.getStylesheets().add("Stylesheet.css");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}