package com.main.cinetrack.model;
/**
 * Classe représentant un film du catalogue CinéTrack.
 * Contient toutes les informations d'un film : titre, genre, durée, note et synopsis.
 */
public class Film {

    // -------------------------------------------------------------------------
    // Attributs privés
    // -------------------------------------------------------------------------
    private int idFilm;
    private String titre;
    private String genre;
    private int duree;       // en minutes
    private double note;     // entre 0 et 10
    private String synopsis;
    private String imagePath;


    // -------------------------------------------------------------------------
    // Constructeur vide
    // -------------------------------------------------------------------------
    public Film() {

    }

    public Film(int idFilm, String titre, String genre, int duree, double note, String synopsis, String imagePath) {
        this.idFilm   = idFilm;
        this.titre    = titre;
        this.genre    = genre;
        this.duree    = duree;
        this.note     = note;
        this.synopsis = synopsis;
        this.imagePath = imagePath;
    }

    // Constructeur sans id pour l'insertion.
    public Film(String titre, String genre, int duree, double note, String synopsis, String imagePath) {
        this.titre    = titre;
        this.genre    = genre;
        this.duree    = duree;
        this.note     = note;
        this.synopsis = synopsis;
        this.imagePath = imagePath;
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    /** @return l'identifiant du film */
    public int getIdFilm() {
        return idFilm;
    }

    /** @return le titre du film */
    public String getTitre() {
        return titre;
    }

    /** @return le genre du film */
    public String getGenre() {
        return genre;
    }

    /** @return la durée du film en minutes */
    public int getDuree() {
        return duree;
    }

    /** @return la note du film (entre 0 et 10) */
    public double getNote() {
        return note;
    }

    /** @return le synopsis du film */
    public String getSynopsis() {
        return synopsis;
    }

    /** @return le chemin de l'image de l'affiche du film*/
    public String getImagePath(){return imagePath;}

    // -------------------------------------------------------------------------
    // Setters
    // -------------------------------------------------------------------------

    /** @param idFilm l'identifiant à définir */
    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    /** @param titre le titre à définir */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /** @param genre le genre à définir */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /** @param duree la durée en minutes à définir */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /** @param imagePath l'affiche optionnelle du film  */
    public void setImage(String imagePath){this.imagePath = imagePath;}

    // -------------------------------------------------------------------------
    // getDureeFormatee — Retourne la durée au format "Xh Ymin"
    // -------------------------------------------------------------------------

    /**
     * Retourne la durée du film dans un format lisible.
     * Exemple : 115 minutes → "1h 55min"
     *
     * @return la durée formatée en heures et minutes
     */
    public String getDureeFormatee() {
        int heures  = duree / 60;   // division entière
        int minutes = duree % 60;   // reste = minutes
        return heures + "h " + minutes + "min";
    }

}