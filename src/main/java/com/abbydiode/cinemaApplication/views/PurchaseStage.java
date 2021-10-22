package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import com.abbydiode.cinemaApplication.Database;
import com.abbydiode.cinemaApplication.models.Showing;
import com.abbydiode.cinemaApplication.models.User;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PurchaseStage extends Stage {
    /**
     * @param app The application that called created this stage
     * @param user The user that signed in
     */
    public PurchaseStage(App app, User user) {
        Database database = app.getDatabase();

        setWidth(1280);
        setHeight(720);
        setTitle("Cinema Application - Purchase Tickets");

        ArrayList<Showing> roomOneShowings = database.getRooms().get(0).getShowings();
        ArrayList<Showing> roomTwoShowings = database.getRooms().get(1).getShowings();

        GridPane grid = new GridPane();
        TableView roomOneTable = new TableView();

        TableColumn<Showing, String> startTimeColumn = new TableColumn<>("Start Time");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        TableColumn<Showing, String> endTimeColumn = new TableColumn<>("Duration");
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Showing, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Showing, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));

        TableColumn<Showing, String> priceColumn = new TableColumn<>("Price");
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        roomOneTable.getColumns().add(startTimeColumn);
        roomOneTable.getColumns().add(endTimeColumn);
        roomOneTable.getColumns().add(titleColumn);
        roomOneTable.getColumns().add(seatsColumn);
        roomOneTable.getColumns().add(priceColumn);

        for (int i = 0; i < roomOneShowings.size(); i++) {
            roomOneTable.getItems().add(roomOneShowings.get(i));
        }

        grid.add(roomOneTable, 0, 0);

        setScene(new Scene(grid));

        show();
    }
}
