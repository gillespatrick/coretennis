package com.gilles.tennis.coretennis.services;

import com.gilles.tennis.coretennis.entity.Match;
import com.gilles.tennis.coretennis.repository.MatchRepoImpl;
import com.gilles.tennis.coretennis.repository.ScoreRepoImpl;

import java.sql.SQLException;

public class MatchService {

    private ScoreRepoImpl scoreRepo;
    private MatchRepoImpl matchRepo;

    public MatchService() {
        this.scoreRepo = new ScoreRepoImpl();
        this.matchRepo = new MatchRepoImpl();
    }

    public void saveNewMatch(Match match) throws SQLException {
        matchRepo.create(match);
        scoreRepo.create(match.getScore());

    }
}
