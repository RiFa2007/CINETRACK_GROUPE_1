package com.main.cinetrack.controllers;

import com.main.cinetrack.main.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.rmi.server.ExportException;

public class ConnexionController {

    @FXML
    private Button button;

    public void changeRoot(){
        try {
            App.setRoot("App");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
