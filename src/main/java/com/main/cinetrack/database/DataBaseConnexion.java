package com.main.cinetrack.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnexion {
    private String url;
    private String user;
    private String password;

    public DataBaseConnexion() {
        Dotenv dotenv = Dotenv.load();
        this.url = dotenv.get("URL");
        this.user = dotenv.get("USER");
        this.password =  dotenv.get("PASSWORD");
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(url, user, password);
    }

}
