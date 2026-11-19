package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Place;
import com.main.cinetrack.database.DataBaseConnexion;

public class PlaceRepository implements IPlaceRepository {

    private final DataBaseConnexion db;

    public PlaceRepository(DataBaseConnexion db) {
        this.db = db;
    }

    @Override
    public void ajouter(Place place) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void modifier(Place place) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void supprimer(int numero, int idSalle) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public Place findById(int numero, int idSalle) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Place> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
