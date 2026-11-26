package com.main.cinetrack.repository;

import java.util.List;
import com.main.cinetrack.model.Film;

public interface IFilmRepository {

    void ajouter(Film film);

    void modifier(Film film);

    void supprimer(int id);

    Film findById(int id);

    List<Film> findAll();

    List<Film> filtrerFilms(String filtre);

    List<Film> rechercher_genre(String genre);
}
