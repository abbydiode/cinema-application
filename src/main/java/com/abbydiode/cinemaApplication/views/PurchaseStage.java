package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import com.abbydiode.cinemaApplication.Database;
import com.abbydiode.cinemaApplication.models.User;
import javafx.stage.Stage;

public class PurchaseStage extends Stage {
    /**
     * @param app The application that called created this stage
     * @param user The user that signed in
     */
    public PurchaseStage(App app, User user) {
        setWidth(1280);
        setHeight(720);
        setTitle("Cinema Application - Purchase Tickets");

        show();
    }
}
