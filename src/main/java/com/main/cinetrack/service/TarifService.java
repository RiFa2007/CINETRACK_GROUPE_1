package com.main.cinetrack.service;

import com.main.cinetrack.model.Tarif;
import com.main.cinetrack.repository.ITarifRepository;

public class TarifService {

    private final ITarifRepository tarifRepository;

    public TarifService(ITarifRepository tarifRepository) {
        this.tarifRepository = tarifRepository;
    }

    public boolean verifierTarif(Tarif tarif) {
        return false;
    }

    public boolean ajouterTarif(Tarif tarif) {
        return true;
    }

    public boolean modifierTarif(Tarif tarif) {

        return true;
    }

    public boolean supprimerTarif(int idTarif) {
        return true;
    }
}
