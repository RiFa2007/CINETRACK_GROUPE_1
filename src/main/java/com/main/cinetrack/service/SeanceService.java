package com.main.cinetrack.service;

import java.util.Calendar;
import java.util.Date;
import com.main.cinetrack.model.Seance;
import com.main.cinetrack.repository.ISeanceRepository;

public class SeanceService{

    private final ISeanceRepository seanceRepository;

    public SeanceService(ISeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    public Seance planifierSeance(int duree, Date date, Date heure) {
        return null;
    }

    public boolean annulerSeance(int idSeance) {
        return false;
    }

    public void modifierSeance(Seance seance) {
    }
}
