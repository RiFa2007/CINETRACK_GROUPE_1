package com.main.cinetrack.exception;

/** Levée quand le genre d'un film est vide. */
public class GenreInvalideException extends Exception {

    public GenreInvalideException(String message) {
        super(message);
    }
}
