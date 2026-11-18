package com.main.cinetrack.controllers.TicketController;
import com.main.cinetrack.enums.Tarif;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

// À adapter : importer Tarif et NumberTextField (composant personnalisé ou bibliothèque)
// import model.Tarif;

/** <<Controller>> */
public class TicketController {

    @FXML
    private TextField nombre_de_client;

    @FXML
    private ChoiceBox<Tarif> Tarif;



    public void AjouterTicket() {
        // TODO : ajouter un ticket
    }

    public void ModifierTicket() {
        // TODO : modifier le ticket sélectionné
    }

    public void AfficherTickets() {
        // TODO : afficher les tickets
    }
}