package com.main.cinetrack.service;

import java.util.HashMap;
import java.util.Map;

import com.main.cinetrack.model.Utilisateur;
import com.main.cinetrack.repository.IUtilisateurRepository;

public class AuthService {


    private final IUtilisateurRepository userRepository;

    public AuthService(IUtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Utilisateur authentifier(String nom) {
        return new Utilisateur();
    }
}
