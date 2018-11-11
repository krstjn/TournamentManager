package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.controller.UserController;
import project.persistence.entities.Match;
import project.persistence.entities.Tournament;
import project.persistence.repositories.IMatchRepository;
import project.service.Interfaces.IMatchService;

import java.util.List;

@Service
public class MatchService implements IMatchService {

    IMatchRepository repository;

    @Autowired
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
    public Match findOne(Long id) {
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

    public List<Match> generateMatches(Tournament tournament){
        // TODO: Implement this
        return null;
    }
}
