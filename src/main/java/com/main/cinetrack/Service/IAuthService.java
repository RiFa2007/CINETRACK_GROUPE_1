package com.main.cinetrack.service;

import com.main.cinetrack.model.User;

public interface IAuthService {

    boolean verifierGestionnaire(String nom);

    boolean verifierSecuriteBruteForce(String nom);

    User authentifier(String nom);
}
