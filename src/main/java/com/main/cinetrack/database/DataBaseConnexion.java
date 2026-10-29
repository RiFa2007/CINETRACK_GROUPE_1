package com.main.cinetrack.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnexion {
    private String url;
    private String user;
    private String password;

    public DataBaseConnexion(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(url, user, password);
    }

}
