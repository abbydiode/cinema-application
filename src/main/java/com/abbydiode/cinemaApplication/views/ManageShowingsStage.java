package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import com.abbydiode.cinemaApplication.Database;
import com.abbydiode.cinemaApplication.models.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ManageShowingsStage extends Stage {
    /**
     * @param app The application that created this stage
     * @param user The user that signed in
     */
    public ManageShowingsStage(App app, User user) {
        Database database = app.getDatabase();

        setWidth(1280);
        setHeight(720);
        setTitle("Cinema Application - Manage Showings");

        BorderPane rootPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu helpMenu = new Menu("Help");
        Menu signOutMenu = new Menu("Sign Out");
        Menu adminMenu = new Menu("Admin");

        MenuItem signOutButton = new MenuItem("Sign Out");
        MenuItem aboutButton = new MenuItem("About");
        MenuItem manageMoviesButton = new MenuItem("Manage Movies");
        MenuItem purchaseButton = new MenuItem("Purchase Tickets");

        signOutMenu.getItems().add(signOutButton);
        helpMenu.getItems().add(aboutButton);

        if (user.getUserType() == UserType.ADMINISTRATOR) {
            menuBar.getMenus().add(adminMenu);
        }

        menuBar.getMenus().addAll(
                helpMenu,
                signOutMenu
        );

        adminMenu.getItems().addAll(
                purchaseButton,
                manageMoviesButton
        );

        VBox topPane = new VBox(menuBar);

        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new LoginStage(app);
                close();
            }
        });

        purchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new PurchaseStage(app, user);
                close();
            }
        });

        manageMoviesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ManageMoviesStage(app, user);
                close();
            }
        });

        GridPane showingsGrid = new GridPane();
        Label roomOneLabel = new Label("Room 1");
        Label roomTwoLabel = new Label("Room 2");
        TableView roomOneTable = new TableView();
        TableView roomTwoTable = new TableView();

        TableColumn<Showing, String> startTimeColumn = new TableColumn<>("Start Time");
        startTimeColumn.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getStartTime().toString()));

        TableColumn<Showing, String> endTimeColumn = new TableColumn<>("End Time");
        endTimeColumn.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getEndTime().toString()));

        TableColumn<Showing, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getMovie().getTitle()));

        TableColumn<Showing, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(showing -> new SimpleStringProperty(Integer.toString(showing.getValue().getSeats())));

        TableColumn<Showing, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(showing -> new SimpleStringProperty("€" + showing.getValue().getMovie().getPrice()));

        TableColumn<Showing, String> startTimeColumn2 = new TableColumn<>("Start Time");
        startTimeColumn2.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getStartTime().toString()));

        TableColumn<Showing, String> endTimeColumn2 = new TableColumn<>("End Time");
        endTimeColumn2.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getStartTime().plusMinutes(showing.getValue().getMovie().getDuration()).toString()));

        TableColumn<Showing, String> titleColumn2 = new TableColumn<>("Title");
        titleColumn2.setCellValueFactory(showing -> new SimpleStringProperty(showing.getValue().getMovie().getTitle()));

        TableColumn<Showing, String> seatsColumn2 = new TableColumn<>("Seats");
        seatsColumn2.setCellValueFactory(showing -> new SimpleStringProperty(Integer.toString(showing.getValue().getSeats())));

        TableColumn<Showing, String> priceColumn2 = new TableColumn<>("Price");
        priceColumn2.setCellValueFactory(showing -> new SimpleStringProperty("€" + showing.getValue().getMovie().getPrice()));

        roomOneTable.getColumns().addAll(
                startTimeColumn,
                endTimeColumn,
                titleColumn,
                seatsColumn,
                priceColumn
        );

        roomTwoTable.getColumns().addAll(startTimeColumn2,
                endTimeColumn2,
                titleColumn2,
                seatsColumn2,
                priceColumn2
        );

        refresh(database, roomOneTable, roomTwoTable);

        showingsGrid.setHgap(8);
        showingsGrid.setVgap(4);
        showingsGrid.setAlignment(Pos.CENTER);
        showingsGrid.add(roomOneLabel, 0, 0);
        showingsGrid.add(roomTwoLabel, 1, 0);
        showingsGrid.add(roomOneTable, 0, 1);
        showingsGrid.add(roomTwoTable, 1, 1);

        GridPane manageShowingsForm = new GridPane();
        Label roomLabel = new Label("Room");
        Label startTimeLabel = new Label("Start Time");
        Label endTimeLabel = new Label("End Time");
        ComboBox selectedRoom = new ComboBox();
        TextField selectedStartTime = new TextField();
        Label selectedEndTimeLabel = new Label();
        Label titleLabel = new Label("Title");
        Label seatsLabel = new Label("Seats");
        Label priceLabel = new Label("Price");
        ComboBox selectedTitle = new ComboBox();
        Label selectedSeatsLabel = new Label();
        Label selectedPrice = new Label();
        Label message = new Label();
        Button addButton = new Button("Add Showing");
        Button clearButton = new Button("Clear");
        message.setTextFill(Color.rgb(176, 0, 32));
        selectedRoom.getItems().addAll("Room 1", "Room 2");
        selectedRoom.getSelectionModel().select(0);

        for (int i = 0; i < database.getMovies().size(); i++) {
            Movie movie = database.getMovies().get(i);
            selectedTitle.getItems().add(movie.getTitle());
        }

        selectedTitle.getSelectionModel().select(0);

        manageShowingsForm.setHgap(8);
        manageShowingsForm.setVgap(4);
        manageShowingsForm.setPadding(new Insets(8));
        manageShowingsForm.add(roomLabel, 0, 0);
        manageShowingsForm.add(startTimeLabel, 0, 1);
        manageShowingsForm.add(endTimeLabel, 0, 2);
        manageShowingsForm.add(selectedRoom, 2, 0);
        manageShowingsForm.add(selectedStartTime, 2, 1);
        manageShowingsForm.add(selectedEndTimeLabel, 2, 2);
        manageShowingsForm.add(titleLabel, 3, 0);
        manageShowingsForm.add(seatsLabel, 3, 1);
        manageShowingsForm.add(priceLabel, 3, 2);
        manageShowingsForm.add(selectedTitle, 4, 0);
        manageShowingsForm.add(selectedSeatsLabel, 4, 1);
        manageShowingsForm.add(selectedPrice, 4, 2);
        manageShowingsForm.add(message, 5, 0);
        manageShowingsForm.add(addButton, 5, 1);
        manageShowingsForm.add(clearButton, 5, 2);

        roomOneTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Showing selectedShowing = (Showing) roomOneTable.getSelectionModel().getSelectedItem();
                if (selectedShowing != null) {
                    rootPane.setBottom(manageShowingsForm);
                    selectedRoom.getSelectionModel().select(0);
                    selectedStartTime.setText(selectedShowing.getStartTime().toString());
                    selectedEndTimeLabel.setText(selectedShowing.getEndTime().toString());
                    selectedTitle.setValue(selectedShowing.getMovie().getTitle());
                    selectedPrice.setText("€" + selectedShowing.getMovie().getPrice());
                    selectedSeatsLabel.setText(Integer.toString(selectedShowing.getSeats()));
                }
            }
        });

        roomTwoTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Showing selectedShowing = (Showing) roomTwoTable.getSelectionModel().getSelectedItem();
                if (selectedShowing != null) {
                    rootPane.setBottom(manageShowingsForm);
                    selectedRoom.getSelectionModel().select(1);
                    selectedStartTime.setText(selectedShowing.getStartTime().toString());
                    selectedEndTimeLabel.setText(selectedShowing.getEndTime().toString());
                    selectedTitle.setValue(selectedShowing.getMovie().getTitle());
                    selectedPrice.setText("€" + selectedShowing.getMovie().getPrice());
                    selectedSeatsLabel.setText(Integer.toString(selectedShowing.getSeats()));
                }
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rootPane.setBottom(null);
                message.setText("");
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Showing selectedShowing = (Showing) (roomOneTable.getSelectionModel().isEmpty() ? roomTwoTable.getSelectionModel().getSelectedItem() : roomOneTable.getSelectionModel().getSelectedItem());
                try {
                    LocalDateTime startTime = LocalDateTime.parse(selectedStartTime.getText());
                    ArrayList<Showing> showings = database.getRooms().get(selectedRoom.getSelectionModel().getSelectedIndex()).getShowings();
                    boolean timeSlotAvailable = true;

                    for (int i = 0; i < showings.size(); i++) {
                        Showing showing = showings.get(i);
                        if (showing.getSafeStartTime().isBefore(startTime.plusMinutes(selectedShowing.getMovie().getDuration())) && showing.getSafeEndTime().isAfter(startTime)) {
                            timeSlotAvailable = false;
                        }
                    }

                    if (timeSlotAvailable) {
                        database.getRooms().get(selectedRoom.getSelectionModel().getSelectedIndex()).insertShowing(new Showing(
                                database.getMovieByName(selectedTitle.getValue().toString()),
                                startTime,
                                selectedShowing.getSeats()
                        ));
                        refresh(database, roomOneTable, roomTwoTable);
                        rootPane.setBottom(null);
                        message.setText("");
                    } else {
                        message.setText("This timeslot is not available, there needs to be a 15 minute break before and after every showing");
                    }
                } catch(Exception exception) {
                    message.setText("Incorrect start time, must be formatted as YYYY-MM-DDTHH:MM:SS (ISO-8601)");
                }
            }
        });

        rootPane.setTop(topPane);
        rootPane.setCenter(showingsGrid);

        setScene(new Scene(rootPane));

        show();
    }

    private void refresh(Database database, TableView roomOneTable, TableView roomTwoTable) {
        ArrayList<Showing> roomOneShowings = database.getRooms().get(0).getShowings();
        ArrayList<Showing> roomTwoShowings = database.getRooms().get(1).getShowings();

        roomOneTable.getItems().clear();
        roomTwoTable.getItems().clear();

        for (int i = 0; i < roomOneShowings.size(); i++) {
            roomOneTable.getItems().add(roomOneShowings.get(i));
        }

        for (int i = 0; i < roomTwoShowings.size(); i++) {
            roomTwoTable.getItems().add(roomTwoShowings.get(i));
        }
    }
}
