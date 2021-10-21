package com.abbydiode.cinemaApplication;

import com.abbydiode.cinemaApplication.models.*;

import java.util.ArrayList;

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

    public void initializeDatabase() {
        insertUser(new User("user", "user", UserType.USER));
        insertUser(new User("admin", "admin", UserType.ADMINISTRATOR));

        insertRoom(new Room());
        insertRoom(new Room());

        insertMovie(new Movie("No Mercy"));
        insertMovie(new Movie("Crash Course"));
        insertMovie(new Movie("Death Toll"));
        insertMovie(new Movie("Dead Air"));
        insertMovie(new Movie("Blood Harvest"));
        insertMovie(new Movie("The Sacrifice"));
        insertMovie(new Movie("The Last Stand"));
        insertMovie(new Movie("Dead Center"));
        insertMovie(new Movie("The Passing"));
        insertMovie(new Movie("Dark Carnival"));
        insertMovie(new Movie("Swamp Fever"));
        insertMovie(new Movie("Hard Rain"));
        insertMovie(new Movie("The Parish"));
    }
}
