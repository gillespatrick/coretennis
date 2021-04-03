package com.gilles.tennis.coretennis;

import com.gilles.tennis.coretennis.entity.Tournoi;
import com.gilles.tennis.coretennis.repository.TournoiRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class TPs {

    public static void main(String[] args) throws SQLException {

        TournoiRepoImpl tournoiRepo = new TournoiRepoImpl();

        // List
        List<Tournoi> list = tournoiRepo.list();

        // Version 1
        for (Tournoi tournoi: list){
            System.out.println(tournoi.getId()+"- "+tournoi.getNom()+" ---> "+tournoi.getCode());
        }

        // Version 2
        //tournoiRepo.list().stream().forEach(tournoi -> System.out.println(tournoi.getId()+"- "+tournoi.getNom()+" ---> "+tournoi.getCode()));
    }
}
