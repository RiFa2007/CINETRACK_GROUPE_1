package com.main.cinetrack.service;

import java.util.Date;
import java.util.List;
import com.main.cinetrack.model.Salle;
import com.main.cinetrack.repository.ISalleRepository;
import com.main.cinetrack.repository.ISeanceRepository;

public class SalleService  {

    private final ISalleRepository salleRepository;
    private final ISeanceRepository seanceRepository;

    public SalleService(ISalleRepository salleRepository, ISeanceRepository seanceRepository) {
        this.salleRepository = salleRepository ;
        this.seanceRepository = seanceRepository ;
    }

    /** Une salle est disponible si aucune séance n'y est prévue à cette date. */
    public boolean disponibiliteSalle(int idSalle, Date date) {
        return false;
    }

     public List<Salle> listerSalle() {
        return null;
    }

     public boolean validerCapacite(int idSalle, int nombrePlaces) {
         return false;
    }
}
