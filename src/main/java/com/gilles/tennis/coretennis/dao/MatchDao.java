package com.gilles.tennis.coretennis.dao;

import com.gilles.tennis.coretennis.DataSourceProvider;
import com.gilles.tennis.coretennis.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDao {
    public void createMatchwithScore(Match match) throws SQLException {
        Connection conn = null;
        try {


            DataSource dataSource = DataSourceProvider.getDataSourceInstance();
            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            // Insert

            PreparedStatement statement = conn.prepareStatement("insert into  MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);


            statement.setLong(1, match.getEpreuve().getId());
            statement.setLong(2, match.getVainqueur().getId());
            statement.setLong(3, match.getFinaliste().getId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()){
                match.setId(rs.getLong(1));
            }


            System.out.println("Match Cree");

            conn = dataSource.getConnection();

            // Insert

            PreparedStatement statement2 = conn.prepareStatement("insert into  SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);


            statement2.setLong(1, match.getScore().getMatch().getId());
            statement2.setByte(2, match.getScore().getSet1());
            statement2.setByte(3, match.getScore().getSet2());

            if (match.getScore().getSet3() == null){
                statement2.setNull(4,Types.TINYINT);
            }else {
                statement2.setByte(4, match.getScore().getSet3());
            }
            if (match.getScore().getSet4() == null){
                statement2.setNull(5,Types.TINYINT);
            }else {
                statement2.setByte(5, match.getScore().getSet4());
            }

            if (match.getScore().getSet5() == null){
                statement2.setNull(6,Types.TINYINT);
            }else {
                statement2.setByte(6, match.getScore().getSet5());
            }


            statement.executeUpdate();
            ResultSet rs2 = statement.getGeneratedKeys();

            if (rs2.next()){
                match.getScore().setId(rs2.getLong(1));
            }

            System.out.println("Score Cree");

            conn.commit();
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



