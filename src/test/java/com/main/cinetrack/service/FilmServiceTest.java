package com.main.cinetrack.service;

import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.exception.FilmIntrouvableException;
import com.main.cinetrack.exception.GenreInvalideException;
import com.main.cinetrack.exception.NoteInvalideException;
import com.main.cinetrack.exception.TitreInvalideException;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilmServiceTest {

    private FilmService service;

    @BeforeEach
    void setUp() {
        DataBaseConnexion dataBaseConnexion = new DataBaseConnexion();
        FilmRepository repository = new FilmRepository(dataBaseConnexion);
        service = new FilmService(repository);
    }

    @Test
    void unFilmAvecUnTitreNullEstRefuse() {
        assertThrows(
                TitreInvalideException.class,          // À REMPLACER par votre exception réelle
                () -> service.ajouterFilm(new Film(null, "genre", 100, 10, "un synopsys", null))     // À REMPLACER par votre vraie méthode
        );
    }

    @Test
    void unFilmAvecTitreNonNullEstAccepte() {
        assertDoesNotThrow(() -> service.ajouterFilm(new Film("Un titre", "genre", 100, 1, "un synopsys", null)));
    }

    @Test
    void unFilmAvecUneNoteNegatifEstRefusee() {
        assertThrows(
                NoteInvalideException.class,
                ()-> service.ajouterFilm(new Film("Un titre", "genre", 100, -1, "un synopsys", null))
        );
    }

    @Test
    void unFilmAvecUneNoteSuperieurA10EstRefusee(){
        assertThrows(
                NoteInvalideException.class,
                ()-> service.ajouterFilm(new Film("Un titre","genre", 100, 11, "un synopsys", null ))
        );
    }

    @Test
    void unFilmAvecUnGenreNullEstRefusee(){
        assertThrows(
                GenreInvalideException.class,
                ()-> service.ajouterFilm(new Film("genre", null, 100, 10, "un synopsys", null))
        );
    }

    @Test
    void unFilmAvecUnIdNegatifLeveUneException(){
        assertThrows(
                FilmIntrouvableException.class,
                ()->service.getFilmById(-1)
        );
    }

}