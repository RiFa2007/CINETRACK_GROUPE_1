package com.main.cinetrack.service;

import java.util.List;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.IFilmRepository;

public class FilmService {

    private final IFilmRepository filmRepository;

    public FilmService(IFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public boolean verifierAjoutFilm(Film film) {
        return false;
    }

     public void ajouterFilm(Film film) {
        filmRepository.ajouter(film);
    }

    public boolean supprimerFilm(int idFilm) {
        return true;
    }

    public List<Film> listeFilm() {
        try {
            System.out.println(filmRepository.findAll());
            return filmRepository.findAll();
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

    // Demande à judi ce que c'est parce que j'en sais rien
    public boolean verifierListefilm() {
        return true;
    }
}
