package com.gilles.tennis.coretennis.repository;

import com.gilles.tennis.coretennis.DataSourceProvider;
import com.gilles.tennis.coretennis.HibernateUtil;
import com.gilles.tennis.coretennis.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JoueurRepoImpl {

    // Creation joueur
    Transaction tx = null;
    Session session = null;
    Joueur joueur = null;

    public void create(Joueur joueur) throws SQLException {

        // Joueur joueur = null;
        
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
        tx.commit();

    }

    /* Mise a jour du joueur
    public void Update(Joueur joueur) throws SQLException {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            // Insert
            PreparedStatement statement = conn.prepareStatement("update JOUEUR set NOM=?, PRENOM=?, SEXE=? where ID=?");

            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3, joueur.getSexe().toString());
            statement.setLong(4, joueur.getId());

            statement.executeUpdate();

            System.out.println("Joueur mise a jour");
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
    */

    // Suppression d'un joueur
    public void delete(Long id) throws SQLException {
        
        joueur = getById(id);

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(joueur);

            System.out.println("Joueur supprime");
     

    }

    // Recherche d'un joueur
    public Joueur getById(Long id) throws SQLException {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class, id);

        System.out.println("Joueur recupere");

        return joueur;

    }

    // Liste des joueurs
    public List<Joueur> list() throws SQLException {

        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("select ID,NOM,PRENOM,SEXE from JOUEUR ");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);

            }

            System.out.println("List des tous les Joueur recupere");
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

        return joueurs;

    }

    /*  public void rename(Long id, String newName) {
        
           try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            joueur = session.get(Joueur.class, id);
            joueur.setNom(newName);
            tx.commit();
            System.out.println("Le joueur a ete renomme avec succe " );

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
     */
}
