package com.main.cinetrack.exception;

/** Levée quand la note d'un film n'est pas comprise entre 0 et 10. */
public class NoteInvalideException extends Exception {

    public NoteInvalideException(String message) {
        super(message);
    }
}
