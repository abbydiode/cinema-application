package com.abbydiode.cinemaApplication.models;

public class Ticket {
    private Showing showing;
    private String name;

    public Ticket(Showing showing, String name) {
        this.showing = showing;
        this.name = name;
    }

    public Showing getShowing() {
        return showing;
    }

    public String getName() {
        return name;
    }
}
