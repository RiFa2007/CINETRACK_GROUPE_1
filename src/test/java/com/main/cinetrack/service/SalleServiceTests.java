package com.main.cinetrack.service;

import com.main.cinetrack.database.DataBaseConnexion;
import com.main.cinetrack.exception.CapaciteNegativeException;
import com.main.cinetrack.exception.NomInvalideException;
import com.main.cinetrack.model.Salle;
import com.main.cinetrack.repository.SalleRepository;
import com.main.cinetrack.repository.SeanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SalleServiceTests {

    SalleService service;

    @BeforeEach
    public void setup() {
        DataBaseConnexion dataBaseConnexion = new DataBaseConnexion();

        SalleRepository salleRepository = new SalleRepository(dataBaseConnexion);
        SeanceRepository seanceRepository = new SeanceRepository(dataBaseConnexion);

        service = new SalleService(salleRepository, seanceRepository);
    }

    @Test
    void uneCapaciteNegativeEstRefusee() {
        Assertions.assertThrows(
                CapaciteNegativeException.class,
                () -> service.ajouterSalle(new Salle("SalleA",-10,"équipement"))
        );
    }

    @Test
    void unNomNullEstRefusee(){
        Assertions.assertThrows(
                NomInvalideException.class,
                () -> service.ajouterSalle(new Salle(null,100,"Irfane")));
    }

}
