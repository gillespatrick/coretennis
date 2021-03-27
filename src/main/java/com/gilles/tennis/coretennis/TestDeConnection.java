/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilles.tennis.coretennis;






import java.sql.*;

public class TestDeConnection {

    public static void main(String[] args) {

        Connection conn = null;
        try {


            // MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TENNIS", "gilles", "gillespatr9ck");

            //Statement statement = conn.createStatement();

            PreparedStatement statement = conn.prepareStatement("select * from JOUEUR where ID = ?");
            long Id = 22L;
            statement.setLong(1,Id);


          /* ResultSet rs = statement.executeQuery("select * from JOUEUR ");
           while (rs.next()){
              String prenom = rs.getString("PRENOM");
               String nom = rs.getString("NOM");
               System.out.println(prenom+" "+nom);

           }*/

            // When i want to display only one player
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                final String prenom = rs.getString("PRENOM");
                final String nom = rs.getString("NOM");
                System.out.println(prenom + " " + nom);
            }
            else {
                System.out.println("Ce joueur n'existe pas");
            }


            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
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
