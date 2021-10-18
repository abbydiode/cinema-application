package com.abbydiode.cinemaApplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage window) throws Exception {
        window.setHeight(720);
        window.setWidth(1280);
        window.setTitle("Cinema Application Base");

        BorderPane pane = new BorderPane();
        Label label = new Label("Hello World");
        pane.setCenter(label);

        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }
}
