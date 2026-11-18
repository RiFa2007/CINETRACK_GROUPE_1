package com.main.cinetrack.service;

import com.main.cinetrack.model.Tarif;
import com.main.cinetrack.repository.ITarifRepository;

public class TarifService implements ITarifService {

    private final ITarifRepository tarifRepository;

    public TarifService(ITarifRepository tarifRepository) {
        this.tarifRepository = tarifRepository;
    }

    @Override
    public boolean verifierTarif(Tarif tarif) {
        return tarif != null && tarif.getPrix() >= 0
                && tarif.getTypeTarif() != null && !tarif.getTypeTarif().isBlank();
    }

    @Override
    public boolean ajouterTarif(Tarif tarif) {
        if (!verifierTarif(tarif)) {
            return false;
        }
        tarifRepository.ajouter(tarif);
        return true;
    }

    @Override
    public boolean modifierTarif(Tarif tarif) {
        if (!verifierTarif(tarif) || tarifRepository.findById(tarif.getIdTarif()) == null) {
            return false;
        }
        tarifRepository.modifier(tarif);
        return true;
    }

    @Override
    public boolean supprimerTarif(int idTarif) {
        if (tarifRepository.findById(idTarif) == null) {
            return false;
        }
        tarifRepository.supprimer(idTarif);
        return true;
    }
}
