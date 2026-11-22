package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Seance;

public interface ISeanceRepository {

    void ajouter(Seance seance);

    void modifier(Seance seance);

    void supprimer(int id);

    Seance findById(int id);

    List<Seance> findAll();
}
