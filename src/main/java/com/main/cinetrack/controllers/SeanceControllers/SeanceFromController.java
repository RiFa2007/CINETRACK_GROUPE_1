package com.main.cinetrack.controllers.SeanceControllers;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
 import jfxtras.scene.control.LocalTimePicker;

/** <<Controller>> */
public class SeanceFromController {

    @FXML
    private ChoiceBox<String> Film;

    @FXML
    private ChoiceBox<String> Salle;

    @FXML
    private DatePicker Date;

    @FXML
    private LocalTimePicker Heure;

    // NumberTextField : composant personnalisé (ou remplacer par TextField)
    @FXML
    private TextField nb_place_disponible;

    public void Ajouter() {
        // TODO : créer une nouvelle séance
    }

    public void Modifier() {
        // TODO : modifier la séance sélectionnée
    }
}