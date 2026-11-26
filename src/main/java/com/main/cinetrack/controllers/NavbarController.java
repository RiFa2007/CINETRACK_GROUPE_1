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
        String filmDirectory = "film/";
        try {
            MainController.changePage(filmDirectory+"film_consultation");
        }catch (Exception e) {
//            MainController.afficherMessage()
            System.out.println(e.getMessage());
        }
    }
    public void goSeance(){

    }
    public void goSalle(){
        try {
            MainController.changePage("Salle/SalleConsultation");
        }catch (Exception e) {
//            MainController.afficherMessage()
            System.out.println(e.getMessage());
        }
    }

    public void goSetting(){

    }
}
