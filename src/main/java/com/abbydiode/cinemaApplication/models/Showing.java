package com.abbydiode.cinemaApplication.models;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private LocalDateTime startTime;
    private int seats;
    private int room;

    public Showing(Movie movie, LocalDateTime startTime, int seats, int room) {
        this.movie = movie;
        this.startTime = startTime;
        this.seats = seats;
        this.room = room;
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
     * @return Available seats for this showing
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @return The date and time that this showing ends
     */
    public LocalDateTime getEndTime() {
        return getStartTime().plusMinutes(getMovie().getDuration());
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

    /**
     * @return The ID of the room
     */
    public int getRoom() {
        return room;
    }
}
