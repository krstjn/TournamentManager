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
    public Tournament create(Tournament tournament, String[] myTeams, LocalDateTime signUp) {

        Set<Team> teams = new HashSet<>();
        if(myTeams != null)
            for (String s: myTeams)
                teams.add(new Team(s, tournament));

        if(teams.size() > 0) {
            tournament.setTeams(teams);
        }

        if(signUp != null){
            tournament.setSignUpExpiration(signUp);
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
        HashMap<Long, Integer> points = new HashMap<>();
        HashMap<Long, Integer> goalsFor = new HashMap<>();
        HashMap<Long, Integer> goalsAgainst = new HashMap<>();
        HashMap<Long, Integer> gamesPlayed = new HashMap<>();
        for(Match match: matches){
            if(match.getPlayed()){

                int home = match.getHomeTeamScore();
                int away = match.getAwayTeamScore();
                Long homeTeam = match.getHomeTeam().getId();
                Long awayTeam = match.getAwayTeam().getId();

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
                                    HashMap<Long,Integer> goalsFor,
                                    HashMap<Long,Integer> goalsAgainst,
                                    HashMap<Long,Integer> points,
                                    HashMap<Long,Integer> gamesPlayed){
        List<ScoreboardItem> scoreboard= new ArrayList<>();
        for(Team team : teams){
            String name = team.getName();
            Long id = team.getId();
            scoreboard.add(new ScoreboardItem(
                    name,
                    gamesPlayed.get(id) == null ? 0 : gamesPlayed.get(id),
                    goalsFor.get(id) == null ? 0 : goalsFor.get(id),
                    goalsAgainst.get(id) == null ? 0 : goalsAgainst.get(id),
                    points.get(id) == null ? 0 : points.get(id))
            );
        }
        Collections.sort(scoreboard, Collections.reverseOrder());
        return scoreboard;
    }
}
