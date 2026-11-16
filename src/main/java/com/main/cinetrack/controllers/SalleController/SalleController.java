package com.main.cinetrack.controllers.SalleController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/** <<Controller>> */
public class SalleController {

    @FXML
    private TextField nom;

    @FXML
    private TextField bar_de_recherche;

    // Le diagramme indique TableView sans type générique
    @FXML
    private TableView ListSeance;

    @FXML
    private Button ajouter;

    public void initialize() {
        // TODO : initialiser la vue
    }

    public void ajouterSalle() {
        // TODO : ajouter une salle
    }

    public void supprimerSalle() {
        // TODO : supprimer la salle sélectionnée
    }

    public void modifierSalle() {
        // TODO : modifier la salle sélectionnée
    }
}
