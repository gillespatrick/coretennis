
package com.gilles.tennis.coretennis;


import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.repository.JoueurRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class TestDeConnection {

    public static void main(String[] args) throws SQLException {

        JoueurRepoImpl joueurRepo = new JoueurRepoImpl();

        /*/ getByID
        Joueur joueur = joueurRepo.getById(54L);
        System.out.println(joueur.getPrenom()+" "+joueur.getNom());
        */

        // Create
        Joueur simpo = new Joueur();
        simpo.setNom("Simpore");
        simpo.setPrenom("Gildas");
        simpo.setSexe('H');
        joueurRepo.create(simpo);
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
