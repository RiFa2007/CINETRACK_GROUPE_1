package com.main.cinetrack.model;

import java.util.Date;
import java.util.UUID;

public class Billet {

    private UUID idBillet;
    private Date heure;
    private Date dateReservation;
    private Place place;
    private Seance seance;
    private Film film;
    private Tarif tarif;

    public Billet() {
    }

    public Billet(UUID idBillet, Date heure, Date dateReservation, Place place, Seance seance, Film film, Tarif tarif) {
        this.idBillet = idBillet;
        this.heure = heure;
        this.dateReservation = dateReservation;
        this.place = place;
        this.seance = seance;
        this.film = film;
        this.tarif = tarif;
    }

    public UUID getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(UUID idBillet) {
        this.idBillet = idBillet;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "Billet{"
                + "idBillet=" + idBillet
                + ", heure=" + heure
                + ", dateReservation=" + dateReservation
                + "}";
    }
}
