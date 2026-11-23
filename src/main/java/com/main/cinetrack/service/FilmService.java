package com.main.cinetrack.service;

import com.main.cinetrack.enums.FiltreFilm;
import com.main.cinetrack.exception.FilmDejaExistantException;
import com.main.cinetrack.exception.FilmException;
import com.main.cinetrack.exception.FilmInvalideException;
import com.main.cinetrack.exception.FilmNotFoundException;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.IFilmRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Service de gestion des films (couche métier).
 *
 * <p>Rôle : valider les données, appliquer les règles de gestion (doublons, existence)
 * puis déléguer l'accès aux données à {@link IFilmRepository}.
 * Le service ne connaît ni JavaFX ni SQL.</p>
 *
 * <p>Toutes les erreurs sortent sous forme de {@link FilmException} (ou d'une sous-classe) :
 * le controller n'a qu'à afficher {@code e.getMessage()}.</p>
 */
public class FilmService {

    public static final int TITRE_MAX    = 150;
    public static final int GENRE_MAX    = 50;
    public static final int DUREE_MAX    = 600;   // minutes
    public static final int SYNOPSIS_MAX = 2000;
    public static final double NOTE_MIN  = 0.0;
    public static final double NOTE_MAX  = 10.0;

    /** Valeur « aucun identifiant à ignorer » pour la détection des doublons. */
    private static final int AUCUN_ID = -1;

    private final IFilmRepository filmRepository;

    public FilmService(IFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    // ------------------------------------------------------------------
    // Création / modification / suppression
    // ------------------------------------------------------------------

    /**
     * Vérifie qu'un film peut être ajouté : données valides et titre non déjà utilisé.
     *
     * @return {@code true} si le film peut être ajouté
     * @throws FilmInvalideException      si une donnée est invalide
     * @throws FilmDejaExistantException  si le titre existe déjà
     */
    public boolean verifierAjoutFilm(Film film) {
        valider(film);
        if (titreDejaUtilise(film.getTitre(), AUCUN_ID)) {
            throw new FilmDejaExistantException(film.getTitre().trim());
        }
        return true;
    }

    /**
     * Ajoute un film après validation.
     *
     * @throws FilmInvalideException     si une donnée est invalide
     * @throws FilmDejaExistantException si le titre existe déjà
     * @throws FilmException             en cas d'erreur d'accès aux données
     */
    public void ajouterFilm(Film film) {
        verifierAjoutFilm(film);
        nettoyer(film);
        accesVoid(() -> filmRepository.ajouter(film));
    }

    /**
     * Modifie un film existant après validation.
     *
     * @throws FilmInvalideException     si une donnée est invalide
     * @throws FilmNotFoundException     si le film n'existe pas
     * @throws FilmDejaExistantException si le titre est pris par un AUTRE film
     * @throws FilmException             en cas d'erreur d'accès aux données
     */
    public void modifierFilm(Film film) {
        valider(film);
        getFilmById(film.getIdFilm());
        if (titreDejaUtilise(film.getTitre(), film.getIdFilm())) {
            throw new FilmDejaExistantException(film.getTitre().trim());
        }
        nettoyer(film);
        accesVoid(() -> filmRepository.modifier(film));
    }

    /**
     * Supprime un film.
     *
     * @return {@code true} si le film a été supprimé
     * @throws FilmNotFoundException si le film n'existe pas
     * @throws FilmException         en cas d'erreur d'accès aux données
     *                               (par exemple film encore lié à des séances)
     */
    public boolean supprimerFilm(int idFilm) {
        getFilmById(idFilm);
        accesVoid(() -> filmRepository.supprimer(idFilm));
        return true;
    }

    // ------------------------------------------------------------------
    // Consultation
    // ------------------------------------------------------------------

    /** @throws FilmNotFoundException si le film n'existe pas */
    public Film getFilmById(int idFilm) {
        Film film = acces(() -> filmRepository.findById(idFilm));
        if (film == null) {
            throw new FilmNotFoundException(idFilm);
        }
        return film;
    }

    /** @return tous les films du catalogue (liste vide s'il n'y en a aucun) */
    public List<Film> listeFilm() {
        List<Film> films = acces(filmRepository::findAll);
        return films == null ? new ArrayList<>() : films;
    }

    /** @return {@code true} si le catalogue contient au moins un film */
    public boolean verifierListefilm() {
        return !listeFilm().isEmpty();
    }

    // ------------------------------------------------------------------
    // Recherche
    // ------------------------------------------------------------------

    /**
     * Recherche par UN critère (celui du ChoiceBox : titre ou genre).
     * La recherche ignore la casse et les accents ; une valeur vide renvoie tout le catalogue.
     */
    public List<Film> rechercherFilms(FiltreFilm filtre, String valeur) {
        if (filtre == null) {
            throw new FilmInvalideException("Choisissez un critère de recherche");
        }
        switch (filtre) {
            case TITRE: return rechercherMulticritere(valeur, null);
            case GENRE: return rechercherMulticritere(null, valeur);
            default:    throw new FilmInvalideException("Critère de recherche inconnu : " + filtre);
        }
    }

    /**
     * Recherche multicritère : tous les critères renseignés doivent correspondre (ET logique).
     * Un critère {@code null} ou vide est ignoré.
     */
    public List<Film> rechercherMulticritere(String titre, String genre) {
        String titreRecherche = normaliser(titre);
        String genreRecherche = normaliser(genre);
        return listeFilm().stream()
                .filter(f -> normaliser(f.getTitre()).contains(titreRecherche))
                .filter(f -> normaliser(f.getGenre()).contains(genreRecherche))
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------
    // Méthodes privées
    // ------------------------------------------------------------------

    /** Valide les champs d'un film. Lève FilmInvalideException avec un message explicite. */
    private void valider(Film film) {
        if (film == null) {
            throw new FilmInvalideException("Le film est obligatoire");
        }
        if (estVide(film.getTitre())) {
            throw new FilmInvalideException("Le titre est obligatoire");
        }
        if (film.getTitre().trim().length() > TITRE_MAX) {
            throw new FilmInvalideException("Le titre ne doit pas dépasser " + TITRE_MAX + " caractères");
        }
        if (estVide(film.getGenre())) {
            throw new FilmInvalideException("Le genre est obligatoire");
        }
        if (film.getGenre().trim().length() > GENRE_MAX) {
            throw new FilmInvalideException("Le genre ne doit pas dépasser " + GENRE_MAX + " caractères");
        }
        if (film.getDuree() <= 0) {
            throw new FilmInvalideException("La durée doit être supérieure à 0 minute");
        }
        if (film.getDuree() > DUREE_MAX) {
            throw new FilmInvalideException("La durée ne doit pas dépasser " + DUREE_MAX + " minutes");
        }
        double note = film.getNote();
        if (Double.isNaN(note) || note < NOTE_MIN || note > NOTE_MAX) {
            throw new FilmInvalideException("La note doit être comprise entre 0 et 10");
        }
        if (film.getSynopsis() != null && film.getSynopsis().length() > SYNOPSIS_MAX) {
            throw new FilmInvalideException("Le synopsis ne doit pas dépasser " + SYNOPSIS_MAX + " caractères");
        }
    }

    /** Retire les espaces superflus avant l'enregistrement. */
    private void nettoyer(Film film) {
        film.setTitre(film.getTitre().trim());
        film.setGenre(film.getGenre().trim());
    }

    /** @return true si un film AUTRE que {@code idAIgnorer} porte déjà ce titre (casse/accents ignorés) */
    private boolean titreDejaUtilise(String titre, int idAIgnorer) {
        String recherche = normaliser(titre);
        return listeFilm().stream()
                .anyMatch(f -> f.getIdFilm() != idAIgnorer && normaliser(f.getTitre()).equals(recherche));
    }

    private boolean estVide(String texte) {
        return texte == null || texte.trim().isEmpty();
    }

    /** Minuscules, sans accents, sans espaces autour : « Amélie » et « amelie » deviennent égaux. */
    private String normaliser(String texte) {
        if (texte == null) {
            return "";
        }
        String sansAccents = Normalizer.normalize(texte.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return sansAccents.toLowerCase();
    }

    /**
     * Exécute un accès au repository : les FilmException passent telles quelles,
     * toute autre erreur (SQL, repository pas encore implémenté…) est convertie en FilmException
     * pour que l'application ne plante jamais.
     */
    private <T> T acces(Supplier<T> action) {
        try {
            return action.get();
        } catch (FilmException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new FilmException("Erreur d'accès aux données des films : " + e.getMessage(), e);
        }
    }

    private void accesVoid(Runnable action) {
        acces(() -> {
            action.run();
            return null;
        });
    }
}
