package com.gilles.tennis.coretennis.services;

import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.repository.JoueurRepoImpl;

import java.sql.SQLException;

public class JoueurService {

    private JoueurRepoImpl joueurRepo;

    public JoueurService() {
        this.joueurRepo = new JoueurRepoImpl();
    }

    // Methode Create
    public void createJoueur(Joueur joueur) throws SQLException {
        joueurRepo.create(joueur);
    }

    // Recuperation d'un joueur
    public Joueur getJoueur(Long id) throws SQLException {
        return joueurRepo.getById(id);

    }
    
    public void rename(Long id, String newName){
        joueurRepo.rename(id, newName);
    }
}
