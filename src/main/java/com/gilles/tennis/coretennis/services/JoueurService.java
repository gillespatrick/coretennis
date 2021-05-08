package com.gilles.tennis.coretennis.services;

import com.gilles.tennis.coretennis.HibernateUtil;
import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.repository.JoueurRepoImpl;

import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JoueurService {

    private JoueurRepoImpl joueurRepo;
    Transaction tx = null;
    Session session = null;
    Joueur joueur = null;

    public JoueurService() {
        this.joueurRepo = new JoueurRepoImpl();
    }

    // Methode Create
    public void createJoueur(Joueur joueur) throws SQLException {

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueurRepo.create(joueur);
            tx.commit();
            
            System.out.println("Le joueur a ete cree avec succes");

        } catch (SQLException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
           

        }
    }
        // Recuperation d'un joueur
    public Joueur getJoueur(Long id) throws SQLException {

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            joueur = joueurRepo.getById(id);

            tx.commit();
            System.out.println("Le joueur a ete lu avec succes");

        } catch (SQLException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
            return joueur;

        }
    }

    public void rename(Long id, String newName) throws SQLException {
        joueur = getJoueur(id);

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
        //  joueur = joueurRepo.getById(id);
        joueur.setNom(newName);
        session.merge(joueur);
        tx.commit();
        System.out.println("Le joueur a ete renomme avec succes");
        if (session != null) {
            session.close();
        }
    }
}
