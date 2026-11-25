package com.main.cinetrack.exception;

/** Levée quand la durée d'un film n'est pas strictement positive. */
public class DureeInvalideException extends Exception {

    public DureeInvalideException(String message) {
        super(message);
    }
}
