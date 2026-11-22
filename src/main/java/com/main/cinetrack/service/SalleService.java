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

    public void supprimerSalle(int id) {
        // On va rajouter des controlles de droit plus tard
        salleRepository.supprimer(id);
    }

    public void modifierSalle(Salle s) throws Exception {
        if(s.getCapacite() < 0) {
            throw new Exception("La capacité de la salle doit être strictement positive");
        }
        if(s.getNom().isEmpty()){
            throw new Exception("Le nom de la salle ne doit pas être vide");
        }
        salleRepository.modifier(s);
    }

    public void ajouterSalle(Salle s) throws Exception {
        if(s.getCapacite() < 0) {
            throw new Exception("La capacité de la salle doit être strictement positive");
        }
        if(s.getNom().isEmpty()){
            throw new Exception("Le nom de la salle ne doit pas être vide");
        }
        salleRepository.ajouter(s);
    }

    /** Une salle est disponible si aucune séance n'y est prévue à cette date. */
    public boolean disponibiliteSalle(int idSalle, Date date) {
        return false;
    }

     public List<Salle> listerSalle() {
        return  salleRepository.findAll();
    }

     public boolean validerCapacite(int idSalle, int nombrePlaces) {
         return false;
    }
}
