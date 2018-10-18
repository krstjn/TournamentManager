package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Team;
import project.persistence.repositories.TeamRepository;
import project.service.Interfaces.ITeamService;

import java.util.Collections;
import java.util.List;

/************************
 * Höfundur: Kristján P.*
 ************************/
@Service
public class TeamService implements ITeamService {
    // Instance Variables
    TeamRepository repository;

    // Dependency Injection
    @Autowired
    public TeamService(TeamRepository repository) {
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
    public List<Team> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Team> findAllReverseOrder() {
        List<Team> teams = repository.findAll();

        // Reverse the list
        Collections.reverse(teams);

        return teams;
    }

    @Override
    public List<Team> findAllByDateReverseOrder(){
        List<Team> teams = repository.findAll();

        // Reverse the list
        Collections.reverse(teams);

        return teams;
    }


    @Override
    public Team findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Team> findByName(String name) {
        return repository.findByName(name);
    }
}
