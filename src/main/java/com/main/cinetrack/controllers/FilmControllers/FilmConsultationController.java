package com.main.cinetrack.controllers.FilmControllers;

import java.util.List;

import com.main.cinetrack.enums.FiltreFilm;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

// À adapter : importer Filtre depuis votre package modèle
// import model.Filtre;

/** <<Controller>> */
public class FilmConsultationController {

    @FXML
    private TextField bar_de_rechreche;

    @FXML
    private ChoiceBox<FiltreFilm> filtre;

    private List<FilmCard> listFilms;

    public void Recherche() {
        // TODO : filtrer listFilms selon bar_de_rechreche et filtre
    }

    public void afficherFilms() {
        // TODO : afficher les FilmCard de listFilms
    }
}

