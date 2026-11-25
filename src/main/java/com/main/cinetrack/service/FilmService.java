package com.main.cinetrack.service;

import java.util.ArrayList;
import java.util.List;

import com.main.cinetrack.enums.FiltreFilm;
import com.main.cinetrack.exception.DureeInvalideException;
import com.main.cinetrack.exception.FilmIntrouvableException;
import com.main.cinetrack.exception.GenreInvalideException;
import com.main.cinetrack.exception.NoteInvalideException;
import com.main.cinetrack.exception.TitreInvalideException;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.IFilmRepository;

public class FilmService {

    private final IFilmRepository filmRepository;

    public FilmService(IFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void ajouterFilm(Film f) throws TitreInvalideException, GenreInvalideException,
            DureeInvalideException, NoteInvalideException {
        valideFilm(f);
        filmRepository.ajouter(f);
    }

    public void modifierFilm(Film f) throws TitreInvalideException, GenreInvalideException,
            DureeInvalideException, NoteInvalideException {
        valideFilm(f);
        filmRepository.modifier(f);
    }

    public void supprimerFilm(int idFilm) {
        // On va rajouter des contrôles de droit plus tard (seul l'admin supprime)
        filmRepository.supprimer(idFilm);
    }

    /** Vérifie les données saisies. Lève une exception si une règle n'est pas respectée. */
    public void valideFilm(Film f) throws TitreInvalideException, GenreInvalideException,
            DureeInvalideException, NoteInvalideException {
        if (f.getTitre() == null || f.getTitre().trim().isEmpty()) {
            throw new TitreInvalideException("Le titre du film ne doit pas être vide");
        }
        if (f.getGenre() == null || f.getGenre().trim().isEmpty()) {
            throw new GenreInvalideException("Le genre du film ne doit pas être vide");
        }
        if (f.getDuree() <= 0) {
            throw new DureeInvalideException("La durée du film doit être strictement positive");
        }
        if (f.getNote() <= 0 || f.getNote() > 10) {
            throw new NoteInvalideException("La note du film doit être comprise entre 1 et 10");
        }
    }

    public List<Film> listeFilm() {
        return filmRepository.findAll();
    }

    public Film getFilmById(int idFilm) throws FilmIntrouvableException {
        Film f = filmRepository.findById(idFilm);
        if (f == null) {
            throw new FilmIntrouvableException("Aucun film trouvé avec l'identifiant " + idFilm);
        }
        return f;
    }

    /** Recherche simple : par titre OU par genre (selon le choix du ChoiceBox). */
    public List<Film> rechercherFilm(FiltreFilm filtre, String texte) {
        if (filtre == FiltreFilm.GENRE) {
            return rechercherFilm("", texte);
        }
        return rechercherFilm(texte, "");
    }

    /** Recherche multicritère : le titre ET le genre doivent contenir le texte saisi. */
    public List<Film> rechercherFilm(String titre, String genre) {
        List<Film> resultat = new ArrayList<>();
        String titreCherche = titre == null ? "" : titre.trim().toLowerCase();
        String genreCherche = genre == null ? "" : genre.trim().toLowerCase();

        for (Film f : filmRepository.findAll()) {
            boolean titreOk = f.getTitre().toLowerCase().contains(titreCherche);
            boolean genreOk = f.getGenre().toLowerCase().contains(genreCherche);
            if (titreOk && genreOk) {
                resultat.add(f);
            }
        }
        return resultat;
    }
}