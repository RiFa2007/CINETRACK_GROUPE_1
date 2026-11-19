package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Categorie;
import com.main.cinetrack.database.DataBaseConnexion;

public class CategorieRepository implements ICategorieRepository {

    private final DataBaseConnexion db;

    public CategorieRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Categorie categorie) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(Categorie categorie) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Categorie findById(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Categorie> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
