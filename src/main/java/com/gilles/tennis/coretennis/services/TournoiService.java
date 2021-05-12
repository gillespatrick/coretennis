package com.gilles.tennis.coretennis.services;

import com.gilles.tennis.coretennis.HibernateUtil;
import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Tournoi;
import com.gilles.tennis.coretennis.repository.TournoiRepoImpl;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {
    
     Transaction tx = null;
    Session session = null;
    Tournoi tournoi = null;

    private TournoiRepoImpl tournoiRepo;

    public TournoiService() {
        this.tournoiRepo = new TournoiRepoImpl();
    }

    // Methode Create
    public void createTournoi(Tournoi tournoi) throws SQLException {
        tournoiRepo.create(tournoi);
    }

  
  
    // Delete Tournoi
    public Tournoi deleteTournoi(Long id) throws SQLException{
         try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoiRepo.delete(id);
            tx.commit();
            
            System.out.println("Le tournoi a ete supprime avec succes");

        } catch (SQLException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
            return tournoi;

        }
       
    }
    
      // Get Tournoi
    public Tournoi getTournoi(Long id) throws SQLException {

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            tournoi = tournoiRepo.getById(id);

            tx.commit();
            System.out.println("Le tournoi a ete lu avec succes");

        } catch (SQLException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
            return tournoi;

        }
    }
}
