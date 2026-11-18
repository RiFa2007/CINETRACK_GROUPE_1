package com.main.cinetrack.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.main.cinetrack.model.Billet;
import com.main.cinetrack.model.Place;
import com.main.cinetrack.model.Seance;

public interface IBilletService {

    boolean verifierDateReservation(Date dateReservation);

    boolean verifierdispoPlace(Seance seance, Place place);

    boolean ajouterReservation(Billet billet);

    void modifierReservation(Billet billet);

    boolean annulerReservation(UUID idBillet);

    float calculerTotalReservation(Billet billet);

    List<Billet> listerReservationBydate(Date date);
}
