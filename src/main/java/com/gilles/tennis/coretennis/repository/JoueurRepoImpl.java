package com.gilles.tennis.coretennis.repository;

import com.gilles.tennis.coretennis.DataSourceProvider;
import com.gilles.tennis.coretennis.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepoImpl {

    // Creation joueur
    public void create (Joueur joueur) throws SQLException {

        Connection conn = null;
        try {

            /*BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(2);
            dataSource.setUrl("jdbc:mysql://localhost:3306/TENNIS");
            dataSource.setUsername("gilles");
            dataSource.setPassword("gillespatr9ck");
            conn = dataSource.getConnection();
            */

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            // Insert

            PreparedStatement statement = conn.prepareStatement("insert into  JOUEUR (NOM,PRENOM,SEXE) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);


            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3,joueur.getSexe().toString());

            statement.executeUpdate();
             ResultSet rs = statement.getGeneratedKeys();

             if (rs.next()){
                joueur.setId(rs.getLong(1));
             }






            System.out.println("Joueur Cree");
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


    // Mise a jour du joueur
    public void Update (Joueur joueur) throws SQLException {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            // Insert

            PreparedStatement statement = conn.prepareStatement("update JOUEUR set NOM=?, PRENOM=?, SEXE=? where ID=?");


            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3,joueur.getSexe().toString());
            statement.setLong(4,joueur.getId());

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


    // Suppression d'un joueur
    public void delete (Long id) throws SQLException {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();



            PreparedStatement statement = conn.prepareStatement("delete from JOUEUR  where ID=?");

            statement.setLong(1,id);

            statement.executeUpdate();


            System.out.println("Joueur supprime");
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


    // Recherche d'un joueur
    public Joueur getById (Long id) throws SQLException {

        Connection conn = null;
        Joueur joueur = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("select NOM,PRENOM,SEXE from JOUEUR  where ID=?");

            statement.setLong(1,id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));

            }


            System.out.println("Joueur recupere");
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

        return joueur;

    }


    // Liste des joueurs
    public List<Joueur> list () throws SQLException {

        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("select ID,NOM,PRENOM,SEXE from JOUEUR ");

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
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





}
