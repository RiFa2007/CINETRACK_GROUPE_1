package com.main.cinetrack.repository;

import java.util.List;
import java.util.UUID;
import com.main.cinetrack.model.Billet;
import com.main.cinetrack.database.DataBaseConnexion;

public class BilletRepository implements IBilletRepository {

    private final DataBaseConnexion db;

    public BilletRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Billet billet) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(Billet billet) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(UUID id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Billet findById(UUID id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Billet> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
