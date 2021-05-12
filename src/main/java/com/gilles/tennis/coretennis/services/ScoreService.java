/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilles.tennis.coretennis.services;

import com.gilles.tennis.coretennis.HibernateUtil;
import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Score;
import com.gilles.tennis.coretennis.repository.JoueurRepoImpl;
import com.gilles.tennis.coretennis.repository.ScoreRepoImpl;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author gillios
 */
public class ScoreService {
    
     private ScoreRepoImpl scoreRepo;
     
    Transaction tx = null;
    Session session = null;
   Score score = null;

    public ScoreService() {
        this.scoreRepo = new ScoreRepoImpl();
    }
    
    
    
    
    // Get Score
    public Score getScore(Long id) throws SQLException {

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            score = scoreRepo.getById(id);
            tx.commit();
            System.out.println("Le score a ete lu avec succes");

        } catch (SQLException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
            return score;

        }
    }
    
}
