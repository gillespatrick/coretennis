
package com.gilles.tennis.coretennis;


import com.gilles.tennis.coretennis.entity.Epreuve;
import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Match;
import com.gilles.tennis.coretennis.entity.Score;
import com.gilles.tennis.coretennis.services.JoueurService;
import com.gilles.tennis.coretennis.services.MatchService;

import java.sql.SQLException;


public class Cour {

    public static void main(String[] args) throws SQLException {

        //JoueurRepoImpl joueurRepo = new JoueurRepoImpl();
        //JoueurService joueurService = new JoueurService();
        MatchService matchService = new MatchService();


        /*/ getByID
        Joueur joueur = joueurRepo.getById(54L);
        System.out.println(joueur.getPrenom()+" "+joueur.getNom());
        */

        // Create
        Match match = new Match();
        Score score = new Score();
        score.setSet1((byte) 3);
        score.setSet2((byte) 4);
        score.setSet3((byte) 6);
        match.setScore(score);
        score.setMatch(match);


        Joueur federer = new Joueur();
        federer.setId(32L);
        Joueur zang = new Joueur();
        zang.setId(51L);
        match.setVainqueur(federer);
        match.setFinaliste(zang);

        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);
        match.setEpreuve(epreuve);


        matchService.saveNewMatch(match);
        System.out.println("ID = "+match.getId());




         /*/ Update
        Joueur simpo = joueurRepo.getById(72L);
        simpo.setNom("Simpore");
        joueurRepo.Update(simpo);
        */

        /*/ Delete
        joueurRepo.delete(72L);
        */

        /*/ List
        List<Joueur> list = joueurRepo.list();

        for (Joueur joueur: list){
            System.out.println(joueur.getPrenom()+" "+joueur.getNom()+" --> "+joueur.getSexe());
        }
        */



    }



}
