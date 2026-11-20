package com.main.cinetrack.model;

public class Utilisateur {
    private String userName;
    private String mp;
    private int id;
    private Role role;

    public Utilisateur(){}

    public Utilisateur(String userName, String mp,Role role) {
        this.userName = userName;
        this.mp = mp;

    }
    public Utilisateur(String userName, String mp, int id,Role role) {
        this.userName = userName;
        this.mp = mp;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getMp() {
        return mp;
    }
    public Role getRole(){return role;}
    public int getId() {
        return id;
    }
}