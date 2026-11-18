package com.main.cinetrack.repository;

import java.util.List;
import java.util.UUID;
import com.main.cinetrack.model.Billet;

public interface IBilletRepository {

    void ajouter(Billet billet);

    void modifier(Billet billet);

    void supprimer(UUID id);

    Billet findById(UUID id);

    List<Billet> findAll();
}
