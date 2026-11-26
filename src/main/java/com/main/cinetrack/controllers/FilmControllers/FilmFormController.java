package com.main.cinetrack.controllers.FilmControllers;

import com.main.cinetrack.model.Film;
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
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "La durée doit être un nombre entier.").show();
            return;
        }

        Film film = new Film(0, titre, genre, duree, note, synopsis, cheminImage);

        // TODO : appeler votre DAO pour enregistrer le film en base
        System.out.println("Film créé : " + film.getTitre());
    }
}