package com.main.cinetrack.service;

import java.util.List;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.IFilmRepository;

public class FilmService implements IFilmService {

    private final IFilmRepository filmRepository;

    public FilmService(IFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public boolean verifierAjoutFilm(Film film) {
        return film != null
                && film.getNomFilm() != null && !film.getNomFilm().isBlank()
                && film.getDureeFilm() > 0;
    }

    @Override
    public boolean ajouterFilm(Film film) {
        if (!verifierAjoutFilm(film)) {
            return false;
        }
        filmRepository.ajouter(film);
        return true;
    }

    @Override
    public boolean supprimerFilm(int idFilm) {
        if (filmRepository.findById(idFilm) == null) {
            return false;
        }
        filmRepository.supprimer(idFilm);
        return true;
    }

    @Override
    public List<Film> listeFilm() {
        return filmRepository.findAll();
    }

    @Override
    public boolean verifierListefilm() {
        List<Film> films = filmRepository.findAll();
        return films != null && !films.isEmpty();
    }
}
