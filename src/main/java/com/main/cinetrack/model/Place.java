package com.main.cinetrack.model;

public class Place {
    private int id;
    private Salle salle;
    private boolean reservee;

    public Place(int id,Salle salle, boolean reservee){
        this.id = id;
        this.salle = salle;
        this.reservee = reservee;
    }

    public int getId() {
        return id;
    }

    public Salle getSalle() {
        return salle;
    }
    public boolean getReservee(){
        return reservee;
    }
}
