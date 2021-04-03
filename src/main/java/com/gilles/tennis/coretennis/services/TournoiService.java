package com.gilles.tennis.coretennis.services;

import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Tournoi;
import com.gilles.tennis.coretennis.repository.TournoiRepoImpl;
import java.sql.SQLException;

public class TournoiService {

    private TournoiRepoImpl tournoiRepo;

    public TournoiService() {
        this.tournoiRepo = new TournoiRepoImpl();
    }

    // Methode Create
    public void createTournoi(Tournoi tournoi) throws SQLException {
        tournoiRepo.create(tournoi);
    }

    // Recuperation d'un joueur
    public Tournoi getTournoi(Long id) throws SQLException {
        return tournoiRepo.getById(id);

    }
}
