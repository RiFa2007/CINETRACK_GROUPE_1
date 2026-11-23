package com.main.cinetrack.model;

import java.util.UUID;

public class Billet {
    private UUID numero;
    private int nombreDeClientAdmin;
    private Tarif tarif;
    private Place place;
    private Seance seance;

    public Billet(UUID numero,
                  int nombreDeClientAdmin,
                  Tarif tarif,
                  Place place,
                  Seance seance){
        this.numero = numero;
        this.nombreDeClientAdmin = nombreDeClientAdmin;
        this.tarif = tarif;
        this.place = place;
        this.seance = seance;
    }

    //Getters
    public int getNombreDeClientAdmin() {
        return nombreDeClientAdmin;
    }
    public UUID getNumero() {
        return numero;
    }
    public Place getPlace() {
        return place;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public Seance getSeance() {
        return seance;
    }

    // Setters
    public void setNombreDeClientAdmin(int nombreDeClientAdmin) {
        this.nombreDeClientAdmin = nombreDeClientAdmin;
    }
    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
    public void setSeance(Seance seance) {
        this.seance = seance;
    }

}
