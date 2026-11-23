package com.main.cinetrack.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.main.cinetrack.model.Billet;
import com.main.cinetrack.model.Place;
import com.main.cinetrack.model.Seance;
import com.main.cinetrack.repository.IBilletRepository;

public class BilletService {

    private final IBilletRepository billetRepository;

    public BilletService(IBilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    public boolean verifierDateReservation(Date dateReservation) {
        return false;
    }

    public boolean verifierdispoPlace(Seance seance, Place place) {
        return false;
    }

    public boolean ajouterReservation(Billet billet) {
        return true;
    }

    public void modifierReservation(Billet billet) {

    }

    public boolean annulerReservation(UUID idBillet) {
        return true;
    }

    public float calculerTotalReservation(Billet billet) {
        return 1;
    }

    public List<Billet> listerReservationBydate(Date date) {
        return null;
    }
}
