package com.gilles.tennis.coretennis.repository;

import com.gilles.tennis.coretennis.DataSourceProvider;
import com.gilles.tennis.coretennis.entity.Match;

import javax.sql.DataSource;
import java.sql.*;


public class MatchRepoImpl {

    // Creation Match
    public void create (Match match) throws SQLException {

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
