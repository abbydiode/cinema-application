package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import com.abbydiode.cinemaApplication.Database;
import com.abbydiode.cinemaApplication.models.Showing;
import com.abbydiode.cinemaApplication.models.Ticket;
import com.abbydiode.cinemaApplication.models.User;
import com.abbydiode.cinemaApplication.models.UserType;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PurchaseStage extends Stage {
    /**
     * @param app The application that created this stage
     * @param user The user that signed in
     */
    public PurchaseStage(App app, User user) {
        Database database = app.getDatabase();

        setWidth(1280);
        setHeight(720);
        setTitle("Cinema Application - Purchase Tickets");

        BorderPane rootPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu helpMenu = new Menu("Help");
        Menu signOutMenu = new Menu("Sign Out");
        Menu adminMenu = new Menu("Admin");

        MenuItem signOutButton = new MenuItem("Sign Out");
        MenuItem aboutButton = new MenuItem("About");
        MenuItem manageShowingsButton = new MenuItem("Manage Showings");
        MenuItem manageMoviesButton = new MenuItem("Manage Movies");
        MenuItem exportButton = new MenuItem("Export to CSV");

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
                manageShowingsButton,
                manageMoviesButton,
                exportButton
        );

        VBox topPane = new VBox(menuBar);

        signOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new LoginStage(app);
                close();
            }
        });

        manageShowingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ManageShowingsStage(app, user);
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

        exportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ArrayList<Showing> showings = database.getRooms().get(0).getShowings();
                showings.addAll(database.getRooms().get(1).getShowings());
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName("showings.csv");
                File file = fileChooser.showSaveDialog(null);
                try {
                    FileWriter writer = new FileWriter(file);
                    for (Showing showing : showings) {
                        writer.append(
                                showing.getStartTime() + "," +
                                showing.getEndTime() + "," +
                                showing.getRoom() + "," +
                                showing.getMovie().getTitle() + "," +
                                showing.getSeats() + "," +
                                showing.getMovie().getPrice() + "\n"
                        );
                    }
                    writer.close();
                } catch (IOException e) {

                }
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

        TextField search = new TextField();

        search.textProperty().addListener((observable, oldstring, newString) -> {
            if (newString.length() > 1) {
                refresh(database, roomOneTable, roomTwoTable, newString);
            } else {
                refresh(database, roomOneTable, roomTwoTable);
            }
        });

        refresh(database, roomOneTable, roomTwoTable);

        showingsGrid.setHgap(8);
        showingsGrid.setVgap(4);
        showingsGrid.setAlignment(Pos.CENTER);
        showingsGrid.add(roomOneLabel, 0, 0);
        showingsGrid.add(roomTwoLabel, 1, 0);
        showingsGrid.add(search, 0, 1);
        showingsGrid.add(roomOneTable, 0, 2);
        showingsGrid.add(roomTwoTable, 1, 2);

        GridPane purchaseForm = new GridPane();
        Label roomLabel = new Label("Room");
        Label startTimeLabel = new Label("Start Time");
        Label endTimeLabel = new Label("End Time");
        Label selectedRoomLabel = new Label();
        Label selectedStartTimeLabel = new Label();
        Label selectedEndTimeLabel = new Label();
        Label titleLabel = new Label("Title");
        Label seatsLabel = new Label("Seats");
        Label nameLabel = new Label("Name");
        Label selectedTitle = new Label();
        ComboBox selectedSeats = new ComboBox();
        TextField selectedName = new TextField();
        Label message = new Label();
        Button purchaseButton = new Button("Purchase");
        Button clearButton = new Button("Clear");
        message.setTextFill(Color.rgb(176, 0, 32));

        for (int i = 1; i < 10; i++) {
            selectedSeats.getItems().add(i);
        }

        selectedSeats.getSelectionModel().select(0);

        purchaseForm.setHgap(8);
        purchaseForm.setVgap(4);
        purchaseForm.setPadding(new Insets(8));
        purchaseForm.add(roomLabel, 0, 0);
        purchaseForm.add(startTimeLabel, 0, 1);
        purchaseForm.add(endTimeLabel, 0, 2);
        purchaseForm.add(selectedRoomLabel, 2, 0);
        purchaseForm.add(selectedStartTimeLabel, 2, 1);
        purchaseForm.add(selectedEndTimeLabel, 2, 2);
        purchaseForm.add(titleLabel, 3, 0);
        purchaseForm.add(seatsLabel, 3, 1);
        purchaseForm.add(nameLabel, 3, 2);
        purchaseForm.add(selectedTitle, 4, 0);
        purchaseForm.add(selectedSeats, 4, 1);
        purchaseForm.add(selectedName, 4, 2);
        purchaseForm.add(message, 5, 0);
        purchaseForm.add(purchaseButton, 5, 1);
        purchaseForm.add(clearButton, 5, 2);

        roomOneTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Showing selectedShowing = (Showing) roomOneTable.getSelectionModel().getSelectedItem();
                if (selectedShowing != null) {
                    rootPane.setBottom(purchaseForm);
                    selectedRoomLabel.setText("Room 1");
                    selectedStartTimeLabel.setText(selectedShowing.getStartTime().toString());
                    selectedEndTimeLabel.setText(selectedShowing.getEndTime().toString());
                    selectedTitle.setText(selectedShowing.getMovie().getTitle());
                }
            }
        });

        roomTwoTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Showing selectedShowing = (Showing) roomTwoTable.getSelectionModel().getSelectedItem();
                if (selectedShowing != null) {
                    rootPane.setBottom(purchaseForm);
                    selectedRoomLabel.setText("Room 2");
                    selectedStartTimeLabel.setText(selectedShowing.getStartTime().toString());
                    selectedEndTimeLabel.setText(selectedShowing.getEndTime().toString());
                    selectedTitle.setText(selectedShowing.getMovie().getTitle());
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

        purchaseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!selectedName.getText().isEmpty()) {
                    Showing selectedShowing = (Showing) (roomOneTable.getSelectionModel().isEmpty() ? roomTwoTable.getSelectionModel().getSelectedItem() : roomOneTable.getSelectionModel().getSelectedItem());
                    int selectedSeatsAmount = Integer.parseInt(selectedSeats.getValue().toString());
                    if (selectedShowing.reserveSeats(selectedSeatsAmount)) {
                        database.insertTicket(new Ticket(selectedShowing, selectedName.getText()));
                        rootPane.setBottom(null);
                        message.setText("");
                        refresh(database, roomOneTable, roomTwoTable);
                    } else {
                        message.setText("You can't reserve any more seats");
                    }
                } else {
                    message.setText("Name cannot be empty");
                }
            }
        });

        setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                new CloseStage(PurchaseStage.this);
            }
        });

        rootPane.setTop(topPane);
        rootPane.setCenter(showingsGrid);

        setScene(new Scene(rootPane));

        show();
    }

    private void refresh(Database database, TableView roomOneTable, TableView roomTwoTable, String query) {
        ArrayList<Showing> roomOneShowings = query == null ? database.getRooms().get(0).getShowings() : database.getRooms().get(0).getShowingsByTitle(query);
        ArrayList<Showing> roomTwoShowings = query == null ? database.getRooms().get(1).getShowings() : database.getRooms().get(1).getShowingsByTitle(query);

        roomOneTable.getItems().clear();
        roomTwoTable.getItems().clear();

        for (Showing roomOneShowing : roomOneShowings) {
            roomOneTable.getItems().add(roomOneShowing);
        }

        for (Showing roomTwoShowing : roomTwoShowings) {
            roomTwoTable.getItems().add(roomTwoShowing);
        }
    }

    private void refresh(Database database, TableView roomOneTable, TableView roomTwoTable) {
        refresh(database, roomOneTable, roomTwoTable, null);
    }
}
