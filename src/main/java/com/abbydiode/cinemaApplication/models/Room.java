package com.abbydiode.cinemaApplication.models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Room {
    ArrayList<Showing> showings = new ArrayList<Showing>();

    public void insertShowing(Showing showing) {
        showings.add(showing);
    }

    public ArrayList<Showing> getShowings() {
        return showings;
    }

    /**
     * @param query Title to search for
     * @return A list of showings that match the query
     */
    public ArrayList<Showing> getShowingsByTitle(String query) {
        return showings.stream().filter(showing -> showing.getMovie().getTitle().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toCollection(ArrayList::new));
    }

    public void deleteRoom(Showing showing) {
        showings.remove(showing);
    }
}
