package com.main.cinetrack.controllers.FilmControllers;

import com.main.cinetrack.main.App;
import com.main.cinetrack.model.Film;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;

public class FilmCard {

    private Film film;
    private HBox root;

    @FXML
    private ImageView imageFilm;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label note;

    public FilmCard(Film film) {
        this.film = film;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    App.class.getResource("/com/main/cinetrack/film/FilmCard.fxml"));
            fxmlLoader.setController(this);   // pas de fx:controller dans le FXML
            root = fxmlLoader.load();         // injecte les champs @FXML
        } catch (IOException e) {
            throw new UncheckedIOException("Impossible de charger FilmCard.fxml", e);
        }

        // Les champs @FXML sont injectés seulement après load()
        title.setText(film.getTitre());
        description.setText(film.getSynopsis());
        note.setText(String.valueOf(film.getNote()));
        if (imageFilm != null || !Objects.equals(film.getImagePath(), "") || film.getImagePath() != null) {
            try{
                imageFilm.setImage(new Image(
                        Objects.requireNonNull(App.class.getResource(film.getImagePath())).toExternalForm()));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public HBox getRoot() {
        return root;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public ImageView getImageFilm() {
        return imageFilm;
    }

    public void setImageFilm(ImageView imageFilm) {
        this.imageFilm = imageFilm;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(Label description) {
        this.description = description;
    }

    public Label getNote() {
        return note;
    }

    public void setNote(Label note) {
        this.note = note;
    }
}