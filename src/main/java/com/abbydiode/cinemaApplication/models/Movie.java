package com.abbydiode.cinemaApplication.models;

public class Movie {
    private String title;
    private double price;
    private int duration;

    public Movie(String title) {
        this.title = title;
    }

    /**
     * @return Title of this movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Price in euros
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return Duration in minutes
     */
    public int getDuration() {
        return duration;
    }
}
