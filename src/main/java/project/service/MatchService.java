package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.controller.UserController;
import project.persistence.entities.Match;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;
import project.persistence.repositories.IMatchRepository;
import project.service.Interfaces.IMatchService;

import java.util.*;

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
        List teams = new ArrayList(tournament.getTeams());
        // If the number of teams is an odd number we add one "ghost" team to the tournament.
        if(teams.size() % 2 != 0) {
            teams.add(new Team("ghost", tournament));
        }

        int nrOfTeams = teams.size();
        int nrOfRounds = tournament.getNrOfRounds();
        List<Match> matches = new ArrayList<Match>();
        int round = 1;

        for(int i = 0; i < nrOfTeams-1; i++) {
            // Split teams into 2 lists.
            List<Team> t1 = new ArrayList<Team>(teams.subList(0, nrOfTeams/2));
            List<Team> t2 = new ArrayList<Team>(teams.subList(nrOfTeams/2, nrOfTeams-1));
            Collections.reverse(t2);

            if(i % 2 == 1) {
                for(int j = 0; i < t1.size(); i++) {
                    for(int x = 0; x < nrOfRounds; x++){
                        if(t1.get(j).getName() == "ghost" || t2.get(j).getName() == "ghost") break;
                        if(x % 2 == 1){
                            matches.add(new Match(t1.get(j),t2.get(j), (round + ((nrOfTeams-1)*x))));
                        } else {
                            matches.add(new Match(t2.get(j),t1.get(j), (round + ((nrOfTeams-1)*x))));
                        }
                    }

                }
            } else {
                for(int j = 0; i < t1.size(); i++) {
                    for(int x = 0; x < nrOfRounds; x++){
                        if(t1.get(j).getName() == "ghost" || t2.get(j).getName() == "ghost") break;
                        if(x % 2 == 1){
                            matches.add(new Match(t2.get(j),t1.get(j), (round + ((nrOfTeams-1)*x))));
                        } else {
                            matches.add(new Match(t1.get(j),t2.get(j), (round + ((nrOfTeams-1)*x))));
                        }
                    }
                }
            }

            // Loading
            teams.add(1, teams.remove(nrOfTeams-1));
            round++;

        }

        return matches;
    }

}
