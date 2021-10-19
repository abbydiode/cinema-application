package com.abbydiode.cinemaApplication.models;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private LocalDateTime startTime;
    private int duration;
    private int seats;
    private float price;

    public Showing(Movie movie, LocalDateTime startTime, int duration, int seats, float price) {
        this.movie = movie;
        this.startTime = startTime;
        this.duration = duration;
        this.seats = seats;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    /**
     * @return Date and time that this showing starts
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @return Duration in minutes
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return Available seats for this showing
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @return Price in euros
     */
    public float getPrice() {
        return price;
    }
}
