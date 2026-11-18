package com.main.cinetrack.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.main.cinetrack.model.Billet;
import com.main.cinetrack.model.Place;
import com.main.cinetrack.model.Seance;
import com.main.cinetrack.repository.IBilletRepository;

public class BilletService implements IBilletService {

    private static final long UN_JOUR_MS = 86_400_000L;

    private final IBilletRepository billetRepository;

    public BilletService(IBilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }

    @Override
    public boolean verifierDateReservation(Date dateReservation) {
        return dateReservation != null
                && !dateReservation.before(new Date(System.currentTimeMillis() - UN_JOUR_MS));
    }

    @Override
    public boolean verifierdispoPlace(Seance seance, Place place) {
        if (seance == null || place == null) {
            return false;
        }
        return billetRepository.findAll().stream().noneMatch(b ->
                b.getSeance() != null && b.getSeance().getIdSeance() == seance.getIdSeance()
                        && memePlace(b.getPlace(), place));
    }

    private boolean memePlace(Place a, Place b) {
        if (a == null || b == null || a.getNumero() != b.getNumero()) {
            return false;
        }
        int salleA = a.getSalle() == null ? -1 : a.getSalle().getIdSalle();
        int salleB = b.getSalle() == null ? -1 : b.getSalle().getIdSalle();
        return salleA == salleB;
    }

    /** Génère l'UUID du billet s'il est absent. */
    @Override
    public boolean ajouterReservation(Billet billet) {
        if (!verifierDateReservation(billet.getDateReservation())
                || !verifierdispoPlace(billet.getSeance(), billet.getPlace())) {
            return false;
        }
        if (billet.getIdBillet() == null) {
            billet.setIdBillet(UUID.randomUUID());
        }
        billetRepository.ajouter(billet);
        return true;
    }

    @Override
    public void modifierReservation(Billet billet) {
        billetRepository.modifier(billet);
    }

    @Override
    public boolean annulerReservation(UUID idBillet) {
        if (billetRepository.findById(idBillet) == null) {
            return false;
        }
        billetRepository.supprimer(idBillet);
        return true;
    }

    @Override
    public float calculerTotalReservation(Billet billet) {
        return billet.getTarif() == null ? 0f : billet.getTarif().getPrix();
    }

    @Override
    public List<Billet> listerReservationBydate(Date date) {
        return billetRepository.findAll().stream()
                .filter(b -> b.getDateReservation() != null && b.getDateReservation().equals(date))
                .collect(Collectors.toList());
    }
}
