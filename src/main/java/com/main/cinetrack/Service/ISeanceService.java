package com.main.cinetrack.service;

import java.util.Date;
import com.main.cinetrack.model.Seance;

public interface ISeanceService {

    Seance planifierSeance(int duree, Date date, Date heure);

    boolean annulerSeance(int idSeance);

    void modifierSeance(Seance seance);
}
