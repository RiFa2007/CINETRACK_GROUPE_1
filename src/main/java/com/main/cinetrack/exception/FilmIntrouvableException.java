package com.main.cinetrack.exception;

/** Levée quand aucun film ne correspond à l'identifiant demandé. */
public class FilmIntrouvableException extends Exception {

    public FilmIntrouvableException(String message) {
        super(message);
    }
}
