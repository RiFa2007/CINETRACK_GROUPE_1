package com.main.cinetrack.service;

import java.util.Date;
import java.util.List;
import com.main.cinetrack.model.Salle;
import com.main.cinetrack.repository.ISalleRepository;
import com.main.cinetrack.repository.ISeanceRepository;

public class SalleService implements ISalleService {

    private final ISalleRepository salleRepository;
    private final ISeanceRepository seanceRepository;

    public SalleService(ISalleRepository salleRepository, ISeanceRepository seanceRepository) {
        this.salleRepository = salleRepository;
        this.seanceRepository = seanceRepository;
    }

    /** Une salle est disponible si aucune séance n'y est prévue à cette date. */
    @Override
    public boolean disponibiliteSalle(int idSalle, Date date) {
        return seanceRepository.findAll().stream().noneMatch(s ->
                s.getSalle() != null && s.getSalle().getIdSalle() == idSalle
                        && s.getDateSeance() != null && s.getDateSeance().equals(date));
    }

    @Override
    public List<Salle> listerSalle() {
        return salleRepository.findAll();
    }

    @Override
    public boolean validerCapacite(int idSalle, int nombrePlaces) {
        Salle salle = salleRepository.findById(idSalle);
        return salle != null && nombrePlaces > 0 && nombrePlaces <= salle.getCapaciteSalle();
    }
}
