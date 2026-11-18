package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Seance;
import com.main.cinetrack.database.DataBaseConnexion;

public class SeanceRepository implements ISeanceRepository {

    private final DataBaseConnexion db;

    public SeanceRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Seance seance) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(Seance seance) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Seance findById(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Seance> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
