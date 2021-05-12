package com.gilles.tennis.coretennis.repository;

import com.gilles.tennis.coretennis.DataSourceProvider;
import com.gilles.tennis.coretennis.HibernateUtil;
import com.gilles.tennis.coretennis.entity.Joueur;
import com.gilles.tennis.coretennis.entity.Score;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreRepoImpl {
    
     Transaction tx = null;
    Session session = null;
    Score score = null;

    // Creation joueur
    public void create (Score score) throws SQLException {

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

            PreparedStatement statement = conn.prepareStatement("insert into  SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            statement.setLong(1, score.getMatch().getId());
            statement.setByte(2, score.getSet1());
            statement.setByte(3, score.getSet2());

            if (score.getSet3() == null){
                statement.setNull(4,Types.TINYINT);
            }else {
                statement.setByte(4, score.getSet3());
            }
            if (score.getSet4() == null){
                statement.setNull(5,Types.TINYINT);
            }else {
                statement.setByte(5, score.getSet4());
            }

            if (score.getSet5() == null){
                statement.setNull(6,Types.TINYINT);
            }else {
                statement.setByte(6, score.getSet5());
            }


            statement.executeUpdate();
             ResultSet rs = statement.getGeneratedKeys();

             if (rs.next()){
                score   .setId(rs.getLong(1));
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
    
    // Recherche d'un score
    public Score getById(Long id) throws SQLException {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        score = session.get(Score.class, id);
        System.out.println("score recupere");
        return score;

    }


}
