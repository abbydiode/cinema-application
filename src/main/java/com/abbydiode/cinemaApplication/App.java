package com.abbydiode.cinemaApplication;

import com.abbydiode.cinemaApplication.views.LoginStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    Database database = new Database();

    @Override
    public void start(Stage stage) {
        database.initializeDatabase();

        new LoginStage(this);
    }

    public Database getDatabase() {
        return database;
    }
}
