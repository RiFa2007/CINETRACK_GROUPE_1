package com.main.cinetrack.model;

public class User {
    private String userName;
    private String mp;
    private Boolean admin ;
    private int id;

    public User(String userName, String mp, Boolean admin ) {
        this.userName = userName;
        this.mp = mp;
        this.admin = admin;
    }
    public User(String userName, String mp, Boolean admin, int id) {
        this.userName = userName;
        this.mp = mp;
        this.admin = admin;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getMp() {
        return mp;
    }
    public Boolean isAdmin(){
        return admin;
    }
    public int getId() {
        return id;
    }
}