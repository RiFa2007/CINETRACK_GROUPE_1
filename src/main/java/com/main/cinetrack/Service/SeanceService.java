package com.main.cinetrack.service;

import java.util.Calendar;
import java.util.Date;
import com.main.cinetrack.model.Seance;
import com.main.cinetrack.repository.ISeanceRepository;

public class SeanceService implements ISeanceService {

    private final ISeanceRepository seanceRepository;

    public SeanceService(ISeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    /**
     * Planifie une séance : la date et l'heure sont fusionnées dans dateSeance.
     * L'identifiant est attribué par la base de données.
     */
    @Override
    public Seance planifierSeance(int duree, Date date, Date heure) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar h = Calendar.getInstance();
        h.setTime(heure);
        cal.set(Calendar.HOUR_OF_DAY, h.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, h.get(Calendar.MINUTE));

        Seance seance = new Seance();
        seance.setDateSeance(cal.getTime());
        seance.setDureeSeance(duree);
        seanceRepository.ajouter(seance);
        return seance;
    }

    @Override
    public boolean annulerSeance(int idSeance) {
        if (seanceRepository.findById(idSeance) == null) {
            return false;
        }
        seanceRepository.supprimer(idSeance);
        return true;
    }

    @Override
    public void modifierSeance(Seance seance) {
        seanceRepository.modifier(seance);
    }
}
