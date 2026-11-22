package com.main.cinetrack.controllers.SeanceControllers;

import com.main.cinetrack.model.Seance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

// À adapter : importer la classe Seance depuis votre package modèle
// import model.Seance;

/** <<Controller>> */
public class SeanceConsutlationController {

    @FXML
    private TableView<Seance> ListSeance;

    @FXML
    private TableColumn<Seance, Integer> colloneId;

    @FXML
    private TableColumn<Seance, String> colloneFilm;

    @FXML
    private TableColumn<Seance, String> colloneSalle;

    @FXML
    private TableColumn<Seance, Button> colloneAction;

    @FXML
    private Button AjouterSeance;

    @FXML
    private TextField bar_de_recherche;

    public void initialize() {
        // TODO : initialiser le TableView et les colonnes
    }

    public void recherche() {
        // TODO : filtrer ListSeance selon bar_de_recherche
    }

    public void ajouterAction() {
        // TODO : ajouter les boutons d'action dans colloneAction
    }
}