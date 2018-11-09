package project.service;

import project.persistence.entities.Match;
import project.persistence.repositories.IMatchRepository;
import project.service.Interfaces.IMatchService;

import java.util.List;

public class MatchService implements IMatchService {

    IMatchRepository repository;

    public MatchService(IMatchRepository repository) {this.repository = repository;}

    @Override
    public Match save(Match match) {
        return repository.save(match);
    }

    @Override
    public void delete(Match match) {
        repository.delete(match);
    }

    @Override
    public Match findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Match> findByTeamId(long teamId) {
        return repository.findByTeamId(teamId);
    }

    @Override
    public List<Match> findByTournamentId(long tournamentId) {
        return repository.findByTournamentId(tournamentId);
    }
}
