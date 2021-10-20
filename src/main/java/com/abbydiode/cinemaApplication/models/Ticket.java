package com.abbydiode.cinemaApplication.models;

public class Ticket {
    private Showing showing;

    public Ticket(Showing showing) {
        this.showing = showing;
    }

    public Showing getShowing() {
        return showing;
    }
}
