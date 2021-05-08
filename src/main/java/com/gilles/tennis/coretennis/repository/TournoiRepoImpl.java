package com.gilles.tennis.coretennis.repository;

import com.gilles.tennis.coretennis.DataSourceProvider;
import com.gilles.tennis.coretennis.HibernateUtil;
import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiRepoImpl {

    // Creation tournoi
    public void create(Tournoi tournoi) throws SQLException {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.persist(tournoi);
            System.out.println("Tournoi ajoute avec succes");
        } catch (Exception e) {
        }
    }

    // Mise a jour du tournoi
    public void Update(Tournoi tournoi) throws SQLException {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            // Insert
            PreparedStatement statement = conn.prepareStatement("update TOURNOI set NOM=?, CODE=? where ID=?");

            statement.setString(1, tournoi.getNom());
            statement.setString(2, tournoi.getCode());
            statement.setLong(3, tournoi.getId());

            statement.executeUpdate();

            System.out.println("Tournoi mise a jour");
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Suppression d'un tournoi
    public void delete(Long id) throws SQLException {
        Session session = null;
        Tournoi tournoi = new Tournoi();
        Transaction tx = null;

        session = HibernateUtil.getSessionFactory().openSession();
        tournoi.setId(id);
        session.delete(tournoi);
        System.out.println("Le tournoi avec l'Id " + tournoi.getId() + " et le nom " + tournoi.getNom() + " a ete correctement supprime");

        if (session != null) {
            session.close();
        }
    }

    // Recherche d'un tournoi
    public Tournoi getById(Long id) throws SQLException {

        Tournoi tournoi = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tournoi = session.get(Tournoi.class, id);

            System.out.println("Tournoi recupere");
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return tournoi;

    }

    // Liste des tournois
    public List<Tournoi> list() throws SQLException {

        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("select ID,NOM,CODE from TOURNOI ");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Tournoi tournoi = new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
                tournois.add(tournoi);
            }

            System.out.println("List des tournois recupere");
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                conn.rollback();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tournois;

    }

}
