package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.controller.UserController;
import project.persistence.entities.Tournament;
import project.persistence.repositories.ITournamentRepository;
import project.service.Interfaces.ITournamentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TournamentService implements ITournamentService {
    // Instance Variables
    private ITournamentRepository repository;

    // Dependency Injection
    @Autowired
    public TournamentService(ITournamentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tournament save(Tournament tournament) {
        return repository.save(tournament);
    }

    @Override
    public void delete(Tournament tournament) {
        repository.delete(tournament);
    }

    @Override
    public List<Tournament> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Tournament> findAllByOrderByIdDesc() {
        List<Tournament> tournaments = repository.findAll();

        // Reverse the list
        Collections.reverse(tournaments);

        return tournaments;
    }

    @Override
    public Tournament findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Tournament> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public ArrayList generateScoreboard(Tournament tournament) {
        // TODO: Implement this
        return null;
    }

}
