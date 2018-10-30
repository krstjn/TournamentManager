package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Tournament;
import project.persistence.repositories.TournamentRepository;
import project.service.Interfaces.ITournamentService;

import java.util.Collections;
import java.util.List;

/************************
 * Höfundur: Kristján P.*
 ************************/
@Service
public class TournamentService implements ITournamentService {
    // Instance Variables
    TournamentRepository repository;

    // Dependency Injection
    @Autowired
    public TournamentService(TournamentRepository repository) {
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
    public List<Tournament> findAllReverseOrder() {
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
    public List<Tournament> findByName(String name) {
        return repository.findByName(name);
    }
}
