package com.abbydiode.cinemaApplication;

import com.abbydiode.cinemaApplication.models.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Random;

public class Database {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Room> rooms = new ArrayList<Room>();

    public void insertUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * @param name Username of the user to find
     * @return The corresponding user or if no user was found null
     */
    public User getUserByName(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public void insertTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void deleteTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void insertMovie(Movie movie) {
        movies.add(movie);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     * @param title Title of the movie to find
     * @return The corresponding movie or if no movie was found null
     */
    public Movie getMovieByName(String title) {
        return movies.stream().filter(movie -> movie.getTitle().equals(title)).findFirst().orElse(null);
    }

    public void deleteMovie(Movie movie) {
        movies.remove(movie);
    }

    public void insertRoom(Room room) {
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void deleteRoom(Room room) {
        rooms.remove(room);
    }

    /**
     * Adds mock data to the database
     */
    public void initializeDatabase() {
        Random random = new Random();

        insertUser(new User("user", "user", UserType.USER));
        insertUser(new User("admin", "admin", UserType.ADMINISTRATOR));

        insertRoom(new Room());
        insertRoom(new Room());

        insertMovie(new Movie("No Mercy", 12, 100));
        insertMovie(new Movie("Crash Course", 15, 120));
        insertMovie(new Movie("Death Toll", 15, 120));
        insertMovie(new Movie("Dead Air", 12, 100));
        insertMovie(new Movie("Blood Harvest", 20, 180));
        insertMovie(new Movie("The Sacrifice", 12, 100));
        insertMovie(new Movie("The Last Stand", 12, 100));
        insertMovie(new Movie("Dead Center", 15, 120));
        insertMovie(new Movie("The Passing", 20, 180));
        insertMovie(new Movie("Dark Carnival", 15, 120));
        insertMovie(new Movie("Swamp Fever", 9, 60));
        insertMovie(new Movie("Hard Rain", 9, 60));
        insertMovie(new Movie("The Parish", 20, 180));

        for (int i = 0; i < movies.size(); i++) {
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(1441058400 + random.nextInt(2678400), 0, ZoneOffset.UTC);
            if (i % 2 == 1) {
                rooms.get(0).insertShowing(new Showing(movies.get(i), dateTime, 100));
            } else {
                rooms.get(1).insertShowing(new Showing(movies.get(i), dateTime, 200));
            }
        }
    }
}
