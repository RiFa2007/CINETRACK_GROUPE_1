package com.main.cinetrack.service;

import java.util.Date;
import java.util.List;
import com.main.cinetrack.model.Salle;

public interface ISalleService {

    boolean disponibiliteSalle(int idSalle, Date date);

    List<Salle> listerSalle();

    boolean validerCapacite(int idSalle, int nombrePlaces);
}
