package com.main.cinetrack.controllers.FilmControllers;

import com.main.cinetrack.controllers.MainController;
import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.enums.FiltreFilm;
import com.main.cinetrack.model.Film;
import com.main.cinetrack.repository.FilmRepository;
import com.main.cinetrack.service.FilmService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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
    public ToggleButton btnTitre;
    @FXML
    public ToggleButton btnGenre;

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
    private final List<Film> tousLesFilms = new ArrayList<>();

    private final FilmService service = new FilmService(new FilmRepository(new DataBaseConnexion()));


    // ----- Appelée automatiquement au chargement du FXML -----
    @FXML
    public void initialize() {
//        System.out.println(service.listeFilm().get(0));

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

    }

    // ----- Affichage du tableau selon la recherche et le filtre -----
    private void afficherFilms() {
        String recherche = bar_de_recherche.getText().toLowerCase().trim();
        List<Film> resultat;

        if (recherche.isEmpty()) {
            resultat = service.listeFilm();
        } else {
            FiltreFilm filtre = btnGenre.isSelected() ? FiltreFilm.GENRE : FiltreFilm.TITRE;
            resultat = service.rechercherFilm(filtre, recherche);
        }
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

    public void PageAjout() throws IOException {
        MainController.changePage("film/Film_Form");
    }
}