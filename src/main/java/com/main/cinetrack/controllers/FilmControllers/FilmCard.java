package com.main.cinetrack.controllers.FilmControllers;


import com.main.cinetrack.model.Film;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class FilmCard {

    private Film film;
    private ImageView imageFilm;
    private Label description;
    private Label note;

    public FilmCard() {
        // Implementer
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