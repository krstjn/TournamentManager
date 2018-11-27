package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Match;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;
import project.persistence.entities.User;
import project.persistence.repositories.ITournamentRepository;
import project.service.Interfaces.IAuthenticationService;
import project.service.Interfaces.ITournamentService;
import project.service.Interfaces.IUserService;
import project.utils.ScoreboardItem;
import project.utils.Sport;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TournamentService implements ITournamentService {
    // Instance Variables
    private ITournamentRepository repository;

    private IUserService userService;
    private IAuthenticationService authenticationService;

    // Dependency Injection
    @Autowired
    public TournamentService(ITournamentRepository repository, IUserService userService, IAuthenticationService authenticationService) {
        this.repository = repository;
        this.userService = userService;
        this.authenticationService = authenticationService;
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
    public List<Tournament> findByNameSearch(String search) { return repository.findByNameSearch(search); }

    @Override
    public Tournament findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Tournament> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Tournament create(Tournament tournament, String[] myTeams) {

        Set<Team> teams = new HashSet<>();
        if(myTeams != null)
            for (String s: myTeams)
                teams.add(new Team(s, tournament));

        if(teams.size() > 0) {
            tournament.setTeams(teams);
        }
        tournament.setUser(userService.findByUsername(authenticationService.getUsername()));
        tournament.setCreated(LocalDateTime.now());

        return this.save(tournament);
    }

    @Override
    public List<ScoreboardItem> generateScoreboard(Tournament tournament) {
        int pointsForWin = 3; // 3 points for a win in football, 2 points otherwise
        if(tournament.getSport() != Sport.Football) pointsForWin = 2;

        List<Match> matches = tournament.getMatches();
        HashMap<String, Integer> points = new HashMap<>();
        HashMap<String, Integer> goalsFor = new HashMap<>();
        HashMap<String, Integer> goalsAgainst = new HashMap<>();
        HashMap<String, Integer> gamesPlayed = new HashMap<>();
        for(Match match: matches){
            if(match.getPlayed()){

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
                    points.put(homeTeam, value + pointsForWin);
                }
                else if(home < away) {
                    int value = points.get(awayTeam) == null ? 0 : points.get(awayTeam);
                    points.put(awayTeam, value + pointsForWin);
                }
                else {
                    int oldHome = points.get(homeTeam) == null ? 0 : points.get(homeTeam);
                    int oldAway = points.get(awayTeam) == null ? 0 : points.get(awayTeam);
                    points.put(homeTeam, oldHome + 1);
                    points.put(awayTeam, oldAway + 1);
                }
            }
        }


        return generateScoreboard(tournament.getTeams(), goalsFor,goalsAgainst,points, gamesPlayed);
    }



    private List<ScoreboardItem> generateScoreboard(Set<Team> teams,
                                    HashMap<String,Integer> goalsFor,
                                    HashMap<String,Integer> goalsAgainst,
                                    HashMap<String,Integer> points,
                                    HashMap<String,Integer> gamesPlayed){
        List<ScoreboardItem> scoreboard= new ArrayList<>();
        for(Team team : teams){
            String name = team.getName();
            scoreboard.add(new ScoreboardItem(
                    name,
                    gamesPlayed.get(name) == null ? 0 : gamesPlayed.get(name),
                    goalsFor.get(name) == null ? 0 : goalsFor.get(name),
                    goalsAgainst.get(name) == null ? 0 : goalsAgainst.get(name),
                    points.get(name) == null ? 0 : points.get(name))
            );
        }
        Collections.sort(scoreboard, Collections.reverseOrder());
        return scoreboard;
    }
}
