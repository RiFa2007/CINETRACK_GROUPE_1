package com.main.cinetrack.repository;

import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.model.Place;

import java.util.List;

public class PlaceRepository implements IPlaceRepository{


    DataBaseConnexion dataBaseConnexion = new DataBaseConnexion();

    public PlaceRepository(DataBaseConnexion dataBaseConnexion) {
        this.dataBaseConnexion = dataBaseConnexion;
    }

    @Override
    public void ajouter(Place Place) {



    }

    @Override
    public void modifier(Place Place) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public Place findById(int id) {
        return null;
    }

    @Override
    public List<Place> findAll() {
        return List.of();
    }

    @Override
    public void reserverPlace() {

    }
}
