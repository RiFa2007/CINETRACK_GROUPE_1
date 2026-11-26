package com.main.cinetrack.service;

import java.util.Date;
import java.util.List;

import com.main.cinetrack.exception.CapaciteNegativeException;
import com.main.cinetrack.exception.IdInvalideException;
import com.main.cinetrack.exception.NomInvalideException;
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

    public void modifierSalle(Salle s) throws NomInvalideException,
                                              CapaciteNegativeException,
                                              IdInvalideException
    {
        if(s.getIdSalle() <= 0){
            throw new IdInvalideException("L'id de la salle est invalide");
        }
        valideSalle(s);
        salleRepository.modifier(s);
    }

    public void ajouterSalle(Salle s) throws NomInvalideException,CapaciteNegativeException {
        valideSalle(s);
        salleRepository.ajouter(s);
    }

    public Salle recupereSalle(int id){
        return salleRepository.findById(id);
    }

    private void valideSalle(Salle s) throws NomInvalideException,CapaciteNegativeException{
        if(s.getCapacite() < 0 ) {
            throw new CapaciteNegativeException("La capacité de la salle doit être strictement positive");
        }
        if(s.getNom() == null || s.getNom().isEmpty() ){
            throw new NomInvalideException("Le nom de la salle ne doit pas être vide");
        }
    }

    /** Une salle est disponible si aucune séance n'y est prévue à cette date. */
    public boolean disponibiliteSalle(int idSalle, Date date) {
        return false;
    }

     public List<Salle> listerSalle() {
        return  salleRepository.findAll();
    }

}
