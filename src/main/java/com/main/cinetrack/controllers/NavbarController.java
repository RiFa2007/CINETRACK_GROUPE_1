package com.main.cinetrack.controllers;

public class NavbarController {

    public void goHome(){
        try {
            MainController.changePage("home");
        }catch (Exception e) {
//            MainController.afficherMessage()
            System.out.println(e.getMessage());
        }
    }
    public void goFilms(){
        try {
            MainController.changePage("page");
        }catch (Exception e) {
//            MainController.afficherMessage()
            System.out.println(e.getMessage());
        }
    }
    public void goSeance(){

    }
    public void goSalle(){

    }

    public void goSetting(){

    }
}
