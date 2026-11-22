package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Salle;
import com.main.cinetrack.database.DataBaseConnexion;

public class SalleRepository implements ISalleRepository {

    private final DataBaseConnexion db;

    public SalleRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Salle salle) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(Salle salle) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Salle findById(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Salle> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
