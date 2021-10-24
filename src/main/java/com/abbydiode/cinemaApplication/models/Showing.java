package com.abbydiode.cinemaApplication.models;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private LocalDateTime startTime;
    private int duration;
    private int seats;
    private double price;

    public Showing(Movie movie, LocalDateTime startTime, int duration, int seats, double price) {
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
     * @return Date and time that the showing starts minus 15 minutes
     */
    public LocalDateTime getSafeStartTime() {
        return startTime.minusMinutes(15);
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
    public double getPrice() {
        return price;
    }

    /**
     * @return The date and time that this showing ends
     */
    public LocalDateTime getEndTime() {
        return getStartTime().plusMinutes(getDuration());
    }

    /**
     * @return The date and time that this showing ends plus 15 minutes
     */
    public LocalDateTime getSafeEndTime() {
        return getEndTime().plusMinutes(15);
    }

    /**
     * @param amountToReserve Amount of seats to reserve
     * @return True if the seats could be reserved, false if there weren't enough seats
     */
    public boolean reserveSeats(int amountToReserve) {
        boolean canReserve = seats - amountToReserve >= 0;
        seats -= canReserve ? amountToReserve : 0;
        return canReserve;
    }
}
