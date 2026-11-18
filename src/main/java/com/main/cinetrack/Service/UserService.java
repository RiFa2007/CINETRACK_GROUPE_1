package com.main.cinetrack.service;

import com.main.cinetrack.model.Role;
import com.main.cinetrack.model.User;
import com.main.cinetrack.repository.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void ajouterCaissier(User user) {
        user.setRole(Role.CAISSIER);
        userRepository.ajouter(user);
    }

    @Override
    public boolean supprimerCaissier(int idUser) {
        User u = userRepository.findById(idUser);
        if (u == null || u.getRole() != Role.CAISSIER) {
            return false;
        }
        userRepository.supprimer(idUser);
        return true;
    }

    @Override
    public void modifierResponsabiliter(int idUser, Role nouveauRole) {
        User u = userRepository.findById(idUser);
        if (u == null) {
            throw new IllegalArgumentException("User introuvable : " + idUser);
        }
        u.setRole(nouveauRole);
        userRepository.modifier(u);
    }
}
