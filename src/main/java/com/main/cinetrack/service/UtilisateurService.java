package com.main.cinetrack.service;

import com.main.cinetrack.model.Role;
import com.main.cinetrack.model.Utilisateur;
import com.main.cinetrack.repository.IUtilisateurRepository;

public class UtilisateurService  {

    private final IUtilisateurRepository userRepository;

    public UtilisateurService(IUtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void ajouterCaissierajouterCaissier(Utilisateur user) {

    }

     public boolean supprimerCaissier(int idUser) {
        return true;
    }


    public void modifierResponsabiliter(int idUser, Role nouveauRole) {

    }
}
