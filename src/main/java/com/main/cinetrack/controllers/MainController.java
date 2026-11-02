package com.main.cinetrack.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentArea;              // injecté par FXMLLoader (champ d'instance)

    private static StackPane staticContentArea; // utilisé par changePage()

    @FXML
    public void initialize() {
        staticContentArea = contentArea;
    }

    protected static void changePage(String page) throws IOException {
        staticContentArea.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(
                MainController.class.getResource("/com/main/cinetrack/" + page + ".fxml"));
        Parent parent = loader.load();
        staticContentArea.getChildren().add(parent);
    }
}