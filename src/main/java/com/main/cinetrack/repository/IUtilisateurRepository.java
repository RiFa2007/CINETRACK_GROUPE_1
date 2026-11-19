package com.main.cinetrack.repository;

import java.util.List;

import com.main.cinetrack.model.User;
import com.main.cinetrack.model.User;

public interface IUtilisateurRepository {

    void ajouter(User utilisateur);

    void modifier(User utilisateur);

    void supprimer(int id);

    User findById(int id);

    List<User> findAll();
}
