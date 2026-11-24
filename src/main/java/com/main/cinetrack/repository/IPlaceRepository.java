package com.main.cinetrack.repository;

import com.main.cinetrack.model.Place;

import java.util.List;

public interface IPlaceRepository {
    void ajouter(Place Place);

    void modifier(Place Place);

    void supprimer(int id);

    Place findById(int id);

    List<Place> findAll();

    void reserverPlace();
}
