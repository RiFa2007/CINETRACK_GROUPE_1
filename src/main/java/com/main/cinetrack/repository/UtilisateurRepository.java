package com.main.cinetrack.repository;

import java.util.List;

import com.main.cinetrack.model.User;
import com.main.cinetrack.database.DataBaseConnexion;

public class UtilisateurRepository implements IUtilisateurRepository {

    private final DataBaseConnexion db;

    public UtilisateurRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(User utilisateur) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(User utilisateur) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
