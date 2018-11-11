package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Team;
import project.persistence.repositories.ITeamRepository;
import project.service.Interfaces.ITeamService;

import java.util.Collections;
import java.util.List;

/************************
 * Höfundur: Kristján P.*
 ************************/
@Service
public class TeamService implements ITeamService {
    // Instance Variables
    private ITeamRepository repository;

    // Dependency Injection
    @Autowired
    public TeamService(ITeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public Team save(Team team) {
        return repository.save(team);
    }

    @Override
    public void delete(Team team) {
        repository.delete(team);
    }

    @Override
    public Team findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Team> findByTournamentId(Long tournamentId) {
        return repository.findByTournamentId(tournamentId);
    }
}
