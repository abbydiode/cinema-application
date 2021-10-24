package com.abbydiode.cinemaApplication.views;

import com.abbydiode.cinemaApplication.App;
import com.abbydiode.cinemaApplication.Database;
import com.abbydiode.cinemaApplication.Utilities;
import com.abbydiode.cinemaApplication.models.Movie;
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
import javafx.stage.Stage;

import java.util.ArrayList;

public class ManageMoviesStage extends Stage {
    /**
     * @param app The application that created this stage
     * @param user The user that signed in
     */
    public ManageMoviesStage(App app, User user) {
        Database database = app.getDatabase();

        setWidth(1280);
        setHeight(720);
        setTitle("Cinema Application - Manage Movies");

        BorderPane rootPane = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu helpMenu = new Menu("Help");
        Menu signOutMenu = new Menu("Sign Out");
        Menu adminMenu = new Menu("Admin");

        MenuItem signOutButton = new MenuItem("Sign Out");
        MenuItem aboutButton = new MenuItem("About");
        MenuItem manageShowingsButton = new MenuItem("Manage Showings");
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
                manageShowingsButton
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

        manageShowingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ManageShowingsStage(app, user);
                close();
            }
        });

        GridPane movieGrid = new GridPane();
        Label moviesLabel = new Label("Movies");
        TableView movieTable = new TableView();

        TableColumn<Movie, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(movie -> new SimpleStringProperty(movie.getValue().getTitle()));

        TableColumn<Movie, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(movie -> new SimpleStringProperty("€" + movie.getValue().getPrice()));

        TableColumn<Movie, String> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(movie -> new SimpleStringProperty(Integer.toString(movie.getValue().getDuration())));


        movieTable.getColumns().addAll(
                titleColumn,
                priceColumn,
                durationColumn
        );

        refresh(database, movieTable);

        movieGrid.setHgap(8);
        movieGrid.setVgap(4);
        movieGrid.setAlignment(Pos.CENTER);
        movieGrid.add(moviesLabel, 0, 0);
        movieGrid.add(movieTable, 0, 1);

        GridPane manageMoviesForm = new GridPane();
        Label titleLabel = new Label("Title");
        Label priceLabel = new Label("Price");
        Label durationLabel = new Label("Duration");
        TextField selectedTitle = new TextField();
        TextField selectedPrice = new TextField();
        TextField selectedDuration = new TextField();
        Label message = new Label();
        Button addButton = new Button("Add Movie");
        Button clearButton = new Button("Clear");
        message.setTextFill(Color.rgb(176, 0, 32));

        manageMoviesForm.setHgap(8);
        manageMoviesForm.setVgap(4);
        manageMoviesForm.setPadding(new Insets(8));
        manageMoviesForm.add(titleLabel, 0, 0);
        manageMoviesForm.add(durationLabel, 0, 1);
        manageMoviesForm.add(priceLabel, 0, 2);
        manageMoviesForm.add(selectedTitle, 1, 0);
        manageMoviesForm.add(selectedDuration, 1, 1);
        manageMoviesForm.add(selectedPrice, 1, 2);
        manageMoviesForm.add(message, 2, 0);
        manageMoviesForm.add(addButton, 2, 1);
        manageMoviesForm.add(clearButton, 2, 2);

        movieTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Movie selectedMovie = (Movie) movieTable.getSelectionModel().getSelectedItem();
                if (selectedMovie != null) {
                    rootPane.setBottom(manageMoviesForm);
                    selectedTitle.setText(selectedMovie.getTitle());
                    selectedPrice.setText(Double.toString(selectedMovie.getPrice()));
                    selectedDuration.setText(Integer.toString(selectedMovie.getDuration()));
                }
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedTitle.setText("");
                selectedDuration.setText("");
                selectedPrice.setText("");
                message.setText("");
            }
        });

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Movie selectedMovie = (Movie) movieTable.getSelectionModel().getSelectedItem();
                Double price = Utilities.tryParseDouble(selectedPrice.getText());
                Integer duration = Utilities.tryParseInteger(selectedDuration.getText());
                if (price != null && price > 0) {
                    if (duration != null && duration > 0) {
                        if (!selectedTitle.getText().isEmpty()) {
                            database.insertMovie(new Movie(selectedTitle.getText(), price, duration));
                            refresh(database, movieTable);
                            message.setText("");
                        } else {
                            message.setText("Movie title cannot be empty");
                        }
                    } else {
                        message.setText("Invalid duration");
                    }
                } else {
                    message.setText("Invalid price");
                }
            }
        });

        rootPane.setTop(topPane);
        rootPane.setCenter(movieGrid);
        rootPane.setBottom(manageMoviesForm);

        setScene(new Scene(rootPane));

        show();
    }

    private void refresh(Database database, TableView moviesTable) {
        ArrayList<Movie> movies = database.getMovies();

        moviesTable.getItems().clear();

        for (int i = 0; i < movies.size(); i++) {
            moviesTable.getItems().add(movies.get(i));
        }
    }
}
