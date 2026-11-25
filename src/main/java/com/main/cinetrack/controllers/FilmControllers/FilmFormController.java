package com.main.cinetrack.controllers.FilmControllers;

import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.exception.DureeInvalideException;
import com.main.cinetrack.exception.GenreInvalideException;
import com.main.cinetrack.exception.NoteInvalideException;
import com.main.cinetrack.exception.TitreInvalideException;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.FilmRepository;
import com.main.cinetrack.service.FilmService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;

public class FilmFormController {

    @FXML private TextField champTitre;
    @FXML private TextArea champSynopsis;
    @FXML private TextField champGenre;
    @FXML private TextField champDuree;
    @FXML private HBox boiteEtoiles;
    @FXML private Label labelNote;
    @FXML private HBox zoneImage;
    @FXML private ImageView apercuImage;
    @FXML private Label labelNomImage;

    private final Label[] etoiles = new Label[10]; // La note du film
    private int note = 0;
    private String cheminImage = null;

    FilmService service = new FilmService(new FilmRepository(new DataBaseConnexion()));

    @FXML
    public void initialize() {
        // Création des 10 étoiles cliquables
        for (int i = 0; i < 10; i++) {
            Label etoile = new Label("☆");
            etoile.getStyleClass().add("etoile");
            final int valeur = i + 1;
            etoile.setOnMouseClicked(e -> definirNote(valeur));
            etoiles[i] = etoile;
            boiteEtoiles.getChildren().add(etoile);
        }
    }

    private void definirNote(int valeur) {
        note = valeur;
        for (int i = 0; i < 10; i++) {
            etoiles[i].setText(i < note ? "★" : "☆");
        }
        labelNote.setText(String.valueOf(note));
    }

    @FXML
    private void choisirImage() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
        File fichier = chooser.showOpenDialog(zoneImage.getScene().getWindow());
        if (fichier != null) {
            cheminImage = fichier.getAbsolutePath();
            apercuImage.setImage(new Image(fichier.toURI().toString()));
            labelNomImage.setText(fichier.getName());
        }
    }

    @FXML
    private void ajouterFilm() {
        String titre = champTitre.getText().trim();
        String genre = champGenre.getText().trim();
        String synopsis = champSynopsis.getText().trim();

        if (titre.isEmpty() || genre.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Le titre et le genre sont obligatoires.").show();
            return;
        }

        int duree;
        try {
            duree = Integer.parseInt(champDuree.getText().trim());
            Film film = new Film(0, titre, genre, duree, note, synopsis, cheminImage);
            service.ajouterFilm(film);
            new Alert(Alert.AlertType.CONFIRMATION, "Film ajouter avec succes");
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "La durée doit être un nombre entier.").show();
            return;
        } catch (TitreInvalideException e) {
            new Alert(Alert.AlertType.WARNING, "Le Titre doit être non null").show();
        } catch (DureeInvalideException e) {
            new Alert(Alert.AlertType.WARNING, "La durée doit être un nombre entier.").show();
        } catch (NoteInvalideException e) {
            new Alert(Alert.AlertType.WARNING, "La note doit être compris entre 1 et ").show();
        } catch (GenreInvalideException e) {
            new Alert(Alert.AlertType.WARNING, "Genre invalide").show();
        }
    }

    public void clearInputs(){
        champTitre.clear();
        champDuree.clear();
        champGenre.clear();
        definirNote(0);
    }

}