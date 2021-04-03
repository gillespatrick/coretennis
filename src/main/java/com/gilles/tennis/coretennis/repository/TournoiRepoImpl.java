package com.gilles.tennis.coretennis.repository;

import com.gilles.tennis.coretennis.DataSourceProvider;
import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepoImpl {

    // Creation tournoi
    public void create (Tournoi tournoi) throws SQLException {

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

            PreparedStatement statement = conn.prepareStatement("insert into  TOURNOI (NOM,CODE) values(?,?)", Statement.RETURN_GENERATED_KEYS);


            statement.setString(1, tournoi.getNom());
            statement.setString(2, tournoi.getCode());


            statement.executeUpdate();
             ResultSet rs = statement.getGeneratedKeys();

             if (rs.next()){
                 tournoi.setId(rs.getLong(1));
             }

            System.out.println("tournoi Cree");
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


    // Mise a jour du tournoi
    public void Update (Tournoi tournoi) throws SQLException {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            // Insert

            PreparedStatement statement = conn.prepareStatement("update TOURNOI set NOM=?, CODE=? where ID=?");


            statement.setString(1, tournoi.getNom());
            statement.setString(2, tournoi.getCode());
            statement.setLong(3,tournoi.getId());

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
    public void delete (Long id) throws SQLException {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();



            PreparedStatement statement = conn.prepareStatement("delete from TOURNOI  where ID=?");

            statement.setLong(1,id);

            statement.executeUpdate();


            System.out.println("Tournoi supprime");
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


    // Recherche d'un tournoi
    public Tournoi getById (Long id) throws SQLException {

        Connection conn = null;
        Tournoi tournoi = null;
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("select NOM,CODE from TOURNOI  where ID=?");

            statement.setLong(1,id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()){
                tournoi = new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));
            }

            System.out.println("Tournoi recupere");
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

        return tournoi;

    }


    // Liste des tournois
    public List<Tournoi> list () throws SQLException {

        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();
        try {

            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("select ID,NOM,CODE from TOURNOI ");

            ResultSet rs = statement.executeQuery();

            while (rs.next()){
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
