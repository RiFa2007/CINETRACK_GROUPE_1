package com.main.cinetrack.model;

public class Tarif {

    private int idTarif;
    private String typeTarif;
    private float prix;

    public Tarif() {
    }

    public Tarif(int idTarif, String typeTarif, float prix) {
        this.idTarif = idTarif;
        this.typeTarif = typeTarif;
        this.prix = prix;
    }

    public int getIdTarif() {
        return idTarif;
    }

    public void setIdTarif(int idTarif) {
        this.idTarif = idTarif;
    }

    public String getTypeTarif() {
        return typeTarif;
    }

    public void setTypeTarif(String typeTarif) {
        this.typeTarif = typeTarif;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Tarif{"
                + "idTarif=" + idTarif
                + ", typeTarif=" + typeTarif
                + ", prix=" + prix
                + "}";
    }
}
