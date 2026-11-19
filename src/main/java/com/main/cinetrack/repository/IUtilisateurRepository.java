package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Utilisateur;

public interface IUtilisateurRepository {

    void ajouter(Utilisateur utilisateur);

    void modifier(Utilisateur utilisateur);

    void supprimer(int id);

    Utilisateur findById(int id);

    List<Utilisateur> findAll();
}
