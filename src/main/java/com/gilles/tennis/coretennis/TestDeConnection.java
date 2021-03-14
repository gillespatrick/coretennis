/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gilles.tennis.coretennis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDeConnection {

    public static void main(String... args) {
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4 
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/TENNIS?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris", "gilles", "gillespatr9ck");

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from JOUEUR where id =12");

            if (rs.next()) {
                final Long id = rs.getLong("id");
                final String nom = rs.getString("nom");
                final String prenom = rs.getString("prenom");

                System.out.println("Le joueur/se dont le numero est " + id + " le prenom " + prenom + " et le nom " + nom);
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
