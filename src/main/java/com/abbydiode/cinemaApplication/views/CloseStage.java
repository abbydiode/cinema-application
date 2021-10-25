package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CloseStage extends Stage {
    /**
     * @param stage The stage that requested to be closed
     */
    public CloseStage(Stage stage) {
        setWidth(256);
        setHeight(144);
        setTitle("INFO");

        GridPane grid = new GridPane();
        Label message = new Label("Close this window?");
        Button closeButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        grid.setHgap(8);
        grid.setVgap(4);
        grid.setAlignment(Pos.CENTER);

        grid.add(message, 0, 0);
        grid.add(closeButton, 0, 1);
        grid.add(cancelButton, 1, 1);

        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                close();
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                close();
            }
        });

        setScene(new Scene((grid)));
        show();
    }
}
