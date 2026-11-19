package com.main.cinetrack.service;

import java.util.HashMap;
import java.util.Map;
import com.main.cinetrack.model.Role;
import com.main.cinetrack.model.User;
import com.main.cinetrack.repository.IUserRepository;

public class AuthService implements IAuthService {

    private static final int MAX_TENTATIVES = 5;

    private final IUserRepository userRepository;
    private final Map<String, Integer> tentatives = new HashMap<>();

    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User trouverParNom(String nom) {
        return userRepository.findAll().stream()
                .filter(u -> u.getNomUser() != null && u.getNomUser().equals(nom))
                .findFirst().orElse(null);
    }

    /** Vrai si l'User existe et a un rôle de gestion (ADMIN ou RESPONSABLE). */
    @Override
    public boolean verifierGestionnaire(String nom) {
        User u = trouverParNom(nom);
        return u != null && (u.getRole() == Role.ADMIN || u.getRole() == Role.RESPONSABLE);
    }

    /** Bloque un identifiant après MAX_TENTATIVES échecs consécutifs. */
    @Override
    public boolean verifierSecuriteBruteForce(String nom) {
        return tentatives.getOrDefault(nom, 0) < MAX_TENTATIVES;
    }

    @Override
    public User authentifier(String nom) {
        if (!verifierSecuriteBruteForce(nom)) {
            throw new SecurityException("Compte temporairement bloqué : " + nom);
        }
        User u = trouverParNom(nom);
        if (u == null) {
            tentatives.merge(nom, 1, Integer::sum);
            return null;
        }
        tentatives.remove(nom);
        return u;
    }
}
