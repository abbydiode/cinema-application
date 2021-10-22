package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import com.abbydiode.cinemaApplication.Database;
import com.abbydiode.cinemaApplication.models.User;
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

public class LoginStage extends Stage {
    /**
     * @param app The application that called created this stage
     */
    public LoginStage(App app) {
        Database database = app.getDatabase();

        setWidth(256);
        setHeight(144);
        setTitle("Sign In");

        GridPane grid = new GridPane();
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button button = new Button("Sign In");
        Label message = new Label();
        message.setTextFill(Color.rgb(176, 0, 32));

        grid.setHgap(8);
        grid.setVgap(4);
        grid.setAlignment(Pos.CENTER);
        grid.add(usernameLabel, 0, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(usernameField, 1, 0);
        grid.add(passwordField, 1, 1);
        grid.add(button, 0, 2);
        grid.add(message, 1, 2);

        setScene(new Scene(grid));

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user =  database.getUserByName(usernameField.getText());
                if (user != null) {
                    if (user.isPasswordEqualTo(passwordField.getText())) {
                        new PurchaseStage(app, user);
                        close();
                    } else {
                        message.setText("Incorrect password");
                    }
                } else {
                    message.setText("Unknown user");
                }
            }
        });

        show();
    }
}
