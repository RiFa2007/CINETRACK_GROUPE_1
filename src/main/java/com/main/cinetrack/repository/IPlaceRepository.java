package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Place;

public interface IPlaceRepository {

    void ajouter(Place place);

    void modifier(Place place);

    void supprimer(int numero, int idSalle);

    Place findById(int numero, int idSalle);

    List<Place> findAll();
}
