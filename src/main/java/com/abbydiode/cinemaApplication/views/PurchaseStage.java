package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import com.abbydiode.cinemaApplication.Database;
import com.abbydiode.cinemaApplication.models.Showing;
import com.abbydiode.cinemaApplication.models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

        BorderPane rootPane = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Application");
        MenuItem signOutButton = new MenuItem("Sign Out");
        menuBar.getMenus().add(menu);
        menu.getItems().add(signOutButton);
        VBox topPane = new VBox(menuBar);

        ArrayList<Showing> roomOneShowings = database.getRooms().get(0).getShowings();
        ArrayList<Showing> roomTwoShowings = database.getRooms().get(1).getShowings();

        GridPane grid = new GridPane();
        Label roomOneLabel = new Label("Room 1");
        Label roomTwoLabel = new Label("Room 2");
        TableView roomOneTable = new TableView();
        TableView roomTwoTable = new TableView();

        TableColumn<Showing, String> startTimeColumn = new TableColumn<>("Start Time");
        startTimeColumn.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getStartTime().toString()));

        TableColumn<Showing, String> endTimeColumn = new TableColumn<>("End Time");
        endTimeColumn.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getStartTime().plusMinutes(showing.getValue().getDuration()).toString()));

        TableColumn<Showing, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getMovie().getTitle()));

        TableColumn<Showing, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(showing -> new SimpleStringProperty(Integer.toString(showing.getValue().getSeats())));

        TableColumn<Showing, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(showing -> new SimpleStringProperty("€" + showing.getValue().getPrice()));

        TableColumn<Showing, String> startTimeColumn2 = new TableColumn<>("Start Time");
        startTimeColumn2.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getStartTime().toString()));

        TableColumn<Showing, String> endTimeColumn2 = new TableColumn<>("End Time");
        endTimeColumn2.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getStartTime().plusMinutes(showing.getValue().getDuration()).toString()));

        TableColumn<Showing, String> titleColumn2 = new TableColumn<>("Title");
        titleColumn2.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getMovie().getTitle()));

        TableColumn<Showing, String> seatsColumn2 = new TableColumn<>("Seats");
        seatsColumn2.setCellValueFactory(showing -> new SimpleStringProperty(Integer.toString(showing.getValue().getSeats())));

        TableColumn<Showing, String> priceColumn2 = new TableColumn<>("Price");
        priceColumn2.setCellValueFactory(showing -> new SimpleStringProperty("€" + showing.getValue().getPrice()));

        roomOneTable.getColumns().add(startTimeColumn);
        roomOneTable.getColumns().add(endTimeColumn);
        roomOneTable.getColumns().add(titleColumn);
        roomOneTable.getColumns().add(seatsColumn);
        roomOneTable.getColumns().add(priceColumn);

        roomTwoTable.getColumns().add(startTimeColumn2);
        roomTwoTable.getColumns().add(endTimeColumn2);
        roomTwoTable.getColumns().add(titleColumn2);
        roomTwoTable.getColumns().add(seatsColumn2);
        roomTwoTable.getColumns().add(priceColumn2);

        for (int i = 0; i < roomOneShowings.size(); i++) {
            roomOneTable.getItems().add(roomOneShowings.get(i));
        }

        for (int i = 0; i < roomTwoShowings.size(); i++) {
            roomTwoTable.getItems().add(roomTwoShowings.get(i));
        }

        grid.setHgap(8);
        grid.setVgap(4);
        grid.setAlignment(Pos.CENTER);
        grid.add(roomOneLabel, 0, 0);
        grid.add(roomTwoLabel, 1, 0);
        grid.add(roomOneTable, 0, 1);
        grid.add(roomTwoTable, 1, 1);

        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new LoginStage(app);
                close();
            }
        });

        rootPane.setTop(topPane);
        rootPane.setCenter(grid);

        setScene(new Scene(rootPane));

        show();
    }
}
