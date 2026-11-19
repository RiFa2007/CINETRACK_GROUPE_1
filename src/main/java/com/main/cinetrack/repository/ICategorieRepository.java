package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Categorie;

public interface ICategorieRepository {

    void ajouter(Categorie categorie);

    void modifier(Categorie categorie);

    void supprimer(int id);

    Categorie findById(int id);

    List<Categorie> findAll();
}
