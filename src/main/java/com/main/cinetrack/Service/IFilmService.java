package com.main.cinetrack.service;

import java.util.List;
import com.main.cinetrack.model.Film;

public interface IFilmService {

    boolean verifierAjoutFilm(Film film);

    boolean ajouterFilm(Film film);

    boolean supprimerFilm(int idFilm);

    List<Film> listeFilm();

    boolean verifierListefilm();
}
