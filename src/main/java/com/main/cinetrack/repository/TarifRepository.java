package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Tarif;
import com.main.cinetrack.database.DataBaseConnexion;

public class TarifRepository implements ITarifRepository {

    private final DataBaseConnexion db;

    public TarifRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Tarif tarif) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(Tarif tarif) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Tarif findById(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Tarif> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
