package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Match;
import project.persistence.entities.Tournament;
import project.persistence.repositories.ITournamentRepository;
import project.service.Interfaces.ITournamentService;
import project.utils.ScoreboardItem;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;

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
    public List generateScoreboard(Tournament tournament) {
        // TODO: Implement this

        Set<Match> matches = tournament.getMatches();
        HashMap<String, Integer> points = new HashMap<>();
        HashMap<String, Integer> goalsFor = new HashMap<>();
        HashMap<String, Integer> goalsAgainst = new HashMap<>();
        HashMap<String, Integer> gamesPlayed = new HashMap<>();
        for(Match match: matches){
            /*if(match.getPlayed()){

            }*/

            int home = match.getHomeTeamScore();
            int away = match.getAwayTeamScore();
            String homeTeam = match.getHomeTeam().getName();
            String awayTeam = match.getAwayTeam().getName();

            int playedHome = gamesPlayed.get(homeTeam) == null ? 0 : gamesPlayed.get(homeTeam);
            gamesPlayed.put(homeTeam, playedHome + 1);

            int playedAway = gamesPlayed.get(awayTeam) == null ? 0 : gamesPlayed.get(awayTeam);
            gamesPlayed.put(awayTeam, playedAway +1);

            int homeFor = goalsFor.get(homeTeam) == null ? 0 : goalsFor.get(homeTeam);
            goalsFor.put(homeTeam, homeFor + home);

            int awayFor = goalsFor.get(awayTeam) == null ? 0 : goalsFor.get(awayTeam);
            goalsFor.put(awayTeam, awayFor + away);

            int homeAgainst = goalsAgainst.get(homeTeam) == null ? 0 : goalsAgainst.get(homeTeam);
            goalsAgainst.put(homeTeam, homeAgainst + away);

            int awayAgainst = goalsAgainst.get(awayTeam) == null ? 0 : goalsAgainst.get(awayTeam);
            goalsAgainst.put(awayTeam, awayAgainst + home);

            if(home > away) {
                int value = points.get(homeTeam) == null ? 0 : points.get(homeTeam);
                points.put(homeTeam, value + 3);
            }
            else if(home < away) {
                int value = points.get(awayTeam) == null ? 0 : points.get(awayTeam);
                points.put(awayTeam, value + 3);
            }
            else {
                int oldHome = points.get(homeTeam) == null ? 0 : points.get(homeTeam);
                int oldAway = points.get(awayTeam) == null ? 0 : points.get(awayTeam);
                points.put(homeTeam, oldHome + 1);
                points.put(awayTeam, oldAway + 1);
            }
        }


        return generateScoreboard(goalsFor,goalsAgainst,points, gamesPlayed);
    }

    private List generateScoreboard(HashMap<String,Integer> goalsFor,
                                    HashMap<String,Integer> goalsAgainst,
                                    HashMap<String,Integer> points,
                                    HashMap<String,Integer> gamesPlayed){
        List<ScoreboardItem> scoreboard= new ArrayList<>();
        for(String team : points.keySet()){
            scoreboard.add(new ScoreboardItem(
                    team,
                    gamesPlayed.get(team),
                    goalsFor.get(team),
                    goalsAgainst.get(team),
                    points.get(team))
            );
        }
        Collections.sort(scoreboard, Collections.reverseOrder());
        return scoreboard;
    }
}
