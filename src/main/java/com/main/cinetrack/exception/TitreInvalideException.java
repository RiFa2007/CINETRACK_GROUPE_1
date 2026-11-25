package com.main.cinetrack.exception;

/** Levée quand le titre d'un film est vide. */
public class TitreInvalideException extends Exception {

    public TitreInvalideException(String message) {
        super(message);
    }
}
