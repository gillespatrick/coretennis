
package com.gilles.tennis.coretennis;


import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.services.JoueurService;
import java.sql.SQLException;


public class TestDeConnection {

    public static void main(String[] args) throws SQLException {

        //JoueurRepoImpl joueurRepo = new JoueurRepoImpl();
        JoueurService joueurService = new JoueurService();

        /*/ getByID
        Joueur joueur = joueurRepo.getById(54L);
        System.out.println(joueur.getPrenom()+" "+joueur.getNom());
        */

        // Create
        Joueur simpo = new Joueur();
        simpo.setNom("Gilles");
        simpo.setPrenom("Patrick");
        simpo.setSexe('H');
        joueurService.createJoueur(simpo);
        System.out.println("ID = "+simpo.getId());


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
