package com.main.cinetrack.model;

public class Utilisateur {
    private String userName;
    private String motDePasse;
    private int id;
    private Role role;

    public Utilisateur(){}

    public Utilisateur(String userName, String motDePasse,Role role) {
        this.userName = userName;
        this.motDePasse = motDePasse;

    }
    public Utilisateur(String userName, String motDePasse, int id,Role role) {
        this.userName = userName;
        this.motDePasse = motDePasse;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public Role getRole(){return role;}
    public int getId() {
        return id;
    }
}