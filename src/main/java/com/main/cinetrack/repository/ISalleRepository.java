package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Salle;

public interface ISalleRepository {

    void ajouter(Salle salle);

    void modifier(Salle salle);

    void supprimer(int id);

    Salle findById(int id);

    List<Salle> findAll();

}
