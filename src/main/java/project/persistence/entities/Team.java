package project.persistence.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    private long id;
    private String name;
    private Set<Match> matches = new HashSet<>();
    private Tournament tournament;

    public Team(){}
    public Team(String name, Tournament tournament){
        this.name = name;
        this.tournament = tournament;
    }
    public Team(String name, Set<Match> matches, Tournament tournament) {
        this.id = id;
        this.name = name;
        this.matches = matches;
        this.tournament = tournament;
    }

    @Id
    @Column(name = "TeamId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @ManyToMany
    @JoinTable(
            name="team_matches",
            joinColumns=@JoinColumn(name="team_ID", referencedColumnName="TeamId"),
            inverseJoinColumns=@JoinColumn(name="match_ID", referencedColumnName="MatchId"))
    public Set<Match> getMatches() { return this.matches; }
    public void setMatches(Set<Match> matches) { this.matches = matches;
    }
    @ManyToOne(fetch = FetchType.EAGER,
            optional = false)
    @JoinColumn(name = "TournamentId")
    public Tournament getTournament() { return this.tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
}
