package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Tarif;

public interface ITarifRepository {

    void ajouter(Tarif tarif);

    void modifier(Tarif tarif);

    void supprimer(int id);

    Tarif findById(int id);

    List<Tarif> findAll();
}
