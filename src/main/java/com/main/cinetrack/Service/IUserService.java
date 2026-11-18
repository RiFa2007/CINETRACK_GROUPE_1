package com.main.cinetrack.service;

import com.main.cinetrack.model.Role;
import com.main.cinetrack.model.User;

public interface IUserService {

    void ajouterCaissier(User user);

    boolean supprimerCaissier(int idUser);

    void modifierResponsabiliter(int idUser, Role nouveauRole);
}
