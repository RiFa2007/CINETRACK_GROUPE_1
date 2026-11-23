package com.main.cinetrack.controllers.FilmControllers;

import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.FilmRepository;
import com.main.cinetrack.service.FilmService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FilmConsultationController {

    // ----- Éléments du FXML -----
    @FXML
    private TextField bar_de_recherche;
    @FXML
    private Button btnFiltre;
    @FXML
    private ToggleButton btnProgrammes;
    @FXML
    private ToggleButton btnMieuxNotes;

    @FXML
    private TableView<Film> tableFilms;
    @FXML
    private TableColumn<Film, String> colTitre;
    @FXML
    private TableColumn<Film, String> colGenre;
    @FXML
    private TableColumn<Film, String> colDuree;
    @FXML
    private TableColumn<Film, String> colSynopsis;
    @FXML
    private TableColumn<Film, Double> colNote;

    // Liste complète des films (non filtrée)
    private List<Film> tousLesFilms = new ArrayList<>();

    private FilmService service ;


    // ----- Appelée automatiquement au chargement du FXML -----
    @FXML
    public void initialize() {
        // 1. Lier chaque colonne à un getter de Film
            colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            colDuree.setCellValueFactory(new PropertyValueFactory<>("dureeFormatee"));
            colSynopsis.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
            colNote.setCellValueFactory(new PropertyValueFactory<>("note"));

            // 2. Afficher la note sous la forme "★ 4.8"
            colNote.setCellFactory(col -> new TableCell<>() {
                @Override
                protected void updateItem(Double note, boolean empty) {
                    super.updateItem(note, empty);
                    getStyleClass().remove("note-cell");
                    if (empty || note == null) {
                        setText(null);
                    } else {
                        setText("★ " + note);
                        getStyleClass().add("note-cell");
                    }
                }
            });



            // 3. Charger les films puis les afficher
            chargerFilms();
            afficherFilms();

            bar_de_recherche.textProperty().addListener((obs, ancien, nouveau) -> afficherFilms());

    }

    // ----- Chargement des films -----
    private void chargerFilms() {
        // TODO : remplacer par votre accès à la base de données (DAO).
        // Données de TEST uniquement :
        tousLesFilms.add(new Film(1, "Inception", "Science-fiction", 148, 4.8,
                "Un voleur s'infiltre dans les rêves.", null));
        tousLesFilms.add(new Film(2, "The Dark Knight", "Action", 152, 4.9,
                "Batman affronte le Joker.", null));
        tousLesFilms.add(new Film(3, "Interstellar", "Science-fiction", 169, 4.6,
                "Des explorateurs traversent un trou de ver.", null));
    }

    // ----- Affichage du tableau selon la recherche et le filtre -----
    private void afficherFilms() {
        String recherche = bar_de_recherche.getText().toLowerCase().trim();
        List<Film> resultat = new ArrayList<>();

        // Filtre par texte (titre ou genre)
        for (Film f : tousLesFilms) {
            if (f.getTitre().toLowerCase().contains(recherche)
                    || f.getGenre().toLowerCase().contains(recherche)) {
                resultat.add(f);
            }
        }

        // Filtre "Mieux notés" : tri par note décroissante
        if (btnMieuxNotes.isSelected()) {
            resultat.sort(Comparator.comparingDouble(Film::getNote).reversed());
        }

        // Filtre "Programmés" : voir la remarque ci-dessous

        tableFilms.setItems(FXCollections.observableArrayList(resultat));
    }

    // ----- Actions liées aux pastilles et au bouton filtre -----
    @FXML
    private void onFiltreChange() {
        afficherFilms();
    }

    @FXML
    private void onBoutonFiltre() {
        // TODO : ouvrir une fenêtre de filtres avancés si besoin
    }
}