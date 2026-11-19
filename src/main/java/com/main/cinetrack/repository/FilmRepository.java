package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.database.DataBaseConnexion;

public class FilmRepository implements IFilmRepository {

    private final DataBaseConnexion db;

    public FilmRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Film film) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(Film film) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Film findById(int id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Film> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
