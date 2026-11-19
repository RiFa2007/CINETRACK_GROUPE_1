package com.main.cinetrack.controllers.FilmControllers;

import java.util.ArrayList;
import java.util.List;

import com.main.cinetrack.enums.FiltreFilm;
import com.main.cinetrack.model.Film;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FilmConsultationController {

    @FXML private TextField bar_de_recherche;       // même nom que le fx:id
    @FXML private ChoiceBox<FiltreFilm> filtre;
    @FXML private VBox conteneurFilms;

    private final List<FilmCard> listFilms = new ArrayList<>();

    @FXML
    public void initialize() {
        filtre.getItems().setAll(FiltreFilm.values());

//        Film f = new Film(1,
//                "String titre",
//                "String genre",
//                100,
//                4,
//                "String synopsis String synopsisString synopsisString synopsis"
//                );   // chemin absolu
//        listFilms.add(new FilmCard(f));

        afficherFilms();
    }

    public void Recherche() {
        // TODO : filtrer listFilms selon bar_de_recherche et filtre
    }

    public void afficherFilms() {
        conteneurFilms.getChildren().clear();
        for (FilmCard card : listFilms) {
            conteneurFilms.getChildren().add(card.getRoot());
        }
    }
}