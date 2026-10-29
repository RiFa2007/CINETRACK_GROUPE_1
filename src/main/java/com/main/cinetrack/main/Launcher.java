package com.main.cinetrack.main;

import com.main.cinetrack.database.DataBaseConnexion;
import io.github.cdimascio.dotenv.Dotenv;

public class Launcher {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String dbUrl = dotenv.get("URL");
        String dbUser = dotenv.get("USER");
        String dbPassword = dotenv.get("PASSWORD");
        DataBaseConnexion connection = new DataBaseConnexion(dbUrl,dbUser,dbPassword);

        App.launch(App.class, args);
    }
}
