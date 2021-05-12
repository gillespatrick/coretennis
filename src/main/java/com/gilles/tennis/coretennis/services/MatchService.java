package com.gilles.tennis.coretennis.services;

import com.gilles.tennis.coretennis.dao.MatchDao;
import com.gilles.tennis.coretennis.entity.Match;
import com.gilles.tennis.coretennis.repository.MatchRepoImpl;
import com.gilles.tennis.coretennis.repository.ScoreRepoImpl;

import java.sql.SQLException;

public class MatchService {

   private ScoreRepoImpl scoreRepo;
     private MatchRepoImpl matchRepo;
    //private MatchDao matchDao;

    public MatchService() {
      this.scoreRepo = new ScoreRepoImpl();
        this.matchRepo = new MatchRepoImpl();

        //matchDao = new MatchDao();
    }


    public void saveNewMatch(Match match) throws SQLException {

        //matchDao.createMatchwithScore(match);
        matchRepo.create(match);
        scoreRepo.create(match.getScore());

    }
}
