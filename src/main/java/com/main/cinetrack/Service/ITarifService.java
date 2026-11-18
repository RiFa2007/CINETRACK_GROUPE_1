package com.main.cinetrack.service;

import com.main.cinetrack.model.Tarif;

public interface ITarifService {

    boolean verifierTarif(Tarif tarif);

    boolean ajouterTarif(Tarif tarif);

    boolean modifierTarif(Tarif tarif);

    boolean supprimerTarif(int idTarif);
}
