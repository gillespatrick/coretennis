/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilles.tennis.coretennis;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class TestDeConnection {

    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/TENNIS");
            dataSource.setUser("gilles");
            dataSource.setPassword("gillespatr9ck");
            conn = dataSource.getConnection();


            // MySQL driver MySQL Connector
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TENNIS", "gilles", "gillespatr9ck");

            //Statement statement = conn.createStatement();

          /* ResultSet rs = statement.executeQuery("select * from JOUEUR ");
           while (rs.next()){
              String prenom = rs.getString("PRENOM");
               String nom = rs.getString("NOM");
               System.out.println(prenom+" "+nom);

           }*/

            // When i want to display only one player
         /*  PreparedStatement statement = conn.prepareStatement("select * from JOUEUR where ID = ?");
            long Id = 22L;
            statement.setLong(1,Id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                final String prenom = rs.getString("PRENOM");
                final String nom = rs.getString("NOM");
                System.out.println(prenom + " " + nom);
            }
            else {
                System.out.println("Ce joueur n'existe pas");
            }
            */
            //**********  End Display ***********

            // Update
          /*  conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement("update JOUEUR set NOM=?, PRENOM=? where ID = ?");

            long Id = 55L;
            String nom = "Yvane";
            String prenom = "Dipande";

            statement.setString(1,nom);
            statement.setString(2,prenom);
            statement.setLong(3,Id);

          int nbSaving =  statement.executeUpdate();

          conn.commit();
            System.out.println("Le nombre d'enregitre put a jour est: "+nbSaving);

        */

            // Insert
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement("insert into  JOUEUR (NOM,PRENOM,SEXE) values(?,?,?)");


            String nom = "Ouaga";
            String prenom = "Dougou";
            String sexe = "F";

            statement.setString(1,nom);
            statement.setString(2,prenom);
            statement.setString(3,sexe);

            int nbSaving = statement.executeUpdate();

            nom = "Obama";
             prenom = "Steve";
             sexe = "H";

            statement.setString(1,nom);
            statement.setString(2,prenom);
            statement.setString(3,sexe);
            // statement.setNull(3, Types.CHAR);

            statement.executeUpdate();

            conn.commit();
            System.out.println("Le nombre d'enregitre put a jour est: "+nbSaving);


            System.out.println("success");
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



}
