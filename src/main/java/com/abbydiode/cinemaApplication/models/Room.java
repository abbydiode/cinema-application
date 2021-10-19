package com.abbydiode.cinemaApplication.models;

import java.util.ArrayList;

public class Room {
    ArrayList<Showing> showings = new ArrayList<Showing>();

    public void insertShowing(Showing showing) {
        showings.add(showing);
    }

    public ArrayList<Showing> getShowings() {
        return showings;
    }

    public void deleteRoom(Showing showing) {
        showings.remove(showing);
    }
}
