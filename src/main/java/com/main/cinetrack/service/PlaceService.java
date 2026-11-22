package com.main.cinetrack.service;

import com.main.cinetrack.exception.PlaceReserveeException;
import com.main.cinetrack.model.Place;
import com.main.cinetrack.repository.IPlaceRepository;

import java.util.List;

public class PlaceService {

    IPlaceRepository placeRepository;

    public PlaceService(IPlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    public void ajouterPlace(Place p){
        placeRepository.ajouter(p);
    }
    public void supprimerPlace(int id) {
        placeRepository.supprimer(id);
    }
    public List<Place> listerPlace(){
        return  placeRepository.findAll();
    }
    public void reserverPlace(Place p) throws PlaceReserveeException{
        if(p.getReservee()){
            throw new PlaceReserveeException("La salle est deja reservé");
        }
        placeRepository.reserverPlace();
    }
}
