package com.main.cinetrack.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentArea;

    private static StackPane staticContentArea;

    @FXML
    public void initialize() {
        staticContentArea = contentArea;
    }

    public static void changePage(String page) throws IOException {
        staticContentArea.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(
                MainController.class.getResource("/com/main/cinetrack/" + page + ".fxml"));
        Parent parent = loader.load();
        staticContentArea.getChildren().add(parent);
    }
}