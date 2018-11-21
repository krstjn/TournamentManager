package project.persistence.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matches")
public class Match {
    // TODO: bæta við breytu sem segir hvort búið sé að spila leik
    private long id;
    private int homeTeamScore;
    private int awayTeamScore;
    @DateTimeFormat(pattern = "YYYY/mm/dd")
    private Date matchDate;
    private int round;
    private String location;
    private Team homeTeam;
    private Team awayTeam;
    private boolean played;
    private Tournament tournament;

    public Match(){}

    public Match(Team homeTeam, Team awayTeam, int round, Tournament tournament) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.round = round;
        played = false;
        this.tournament = tournament;
    }

    @Id
    @Column(name = "MatchId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }

    @ManyToOne
    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }

    @ManyToOne
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getPlayed() { return played; }

    public void setPlayed(boolean played) { this.played = played; }
}
