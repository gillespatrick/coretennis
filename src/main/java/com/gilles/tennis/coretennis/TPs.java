package com.gilles.tennis.coretennis;

import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Tournoi;
import com.gilles.tennis.coretennis.repository.TournoiRepoImpl;
import com.gilles.tennis.coretennis.services.JoueurService;
import com.gilles.tennis.coretennis.services.TournoiService;

import java.sql.SQLException;
import java.util.List;

public class TPs {

    public static void main(String[] args) throws SQLException {

        //TournoiRepoImpl tournoiRepo = new TournoiRepoImpl();
        JoueurService joueurService = new JoueurService();
        TournoiService tournoiService = new TournoiService();

        // List
       // List<Tournoi> list = tournoiRepo.list();

        /*/ Version 1
        for (Tournoi tournoi: list){
            System.out.println(tournoi.getId()+"- "+tournoi.getNom()+" ---> "+tournoi.getCode());
        }
        */
        // Version 2
        //tournoiRepo.list().stream().forEach(tournoi -> System.out.println(tournoi.getId()+"- "+tournoi.getNom()+" ---> "+tournoi.getCode()));




         // getByID de joueur
      //  Joueur joueur = joueurService.getJoueur(5L);
      //  System.out.println(joueur.getPrenom()+" "+joueur.getNom());


        /*/ Create tournoi et getIdTournoi
        Tournoi tournoi = new Tournoi();
        tournoi.setNom("New York City");
        tournoi.setCode("NY");
        tournoiService.createTournoi(tournoi);
        System.out.println("ID = "+tournoi.getId());
        */
        Tournoi tournoi = tournoiService.getTournoi(5L);
        System.out.println(tournoi.getNom()+" ---> "+tournoi.getCode());

    }
}
