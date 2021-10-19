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

    public void InitializeDatabase() {
        insertUser(new User("user", "user", UserType.USER));
        insertUser(new User("admin", "admin", UserType.ADMINISTRATOR));

        insertRoom(new Room());
        insertRoom(new Room());
    }
}
