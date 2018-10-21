package project.persistence.entities;

import org.springframework.format.annotation.DateTimeFormat;
import project.utils.TournamentType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {
    private long id;

    private String name;
    private Date created = new Date();
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date signUpExpiration;
    private int teamCount = 0;
    private int maxTeams = 10;
    @Enumerated(EnumType.STRING)
    private TournamentType type = TournamentType.GroupStage;
    private Set<Team> teams = new HashSet<>();

    public Tournament() {
    }
    public Tournament(String name, Date signUpExpiration, int teamCount, int maxTeams, TournamentType type) {
        this.name = name;
        this.signUpExpiration = signUpExpiration;
        this.teamCount = teamCount;
        this.maxTeams = maxTeams;
        this.type = type;
    }

    @OneToMany(mappedBy = "tournament",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Team> getTeams() { return teams; }
    public void setTeams(Set<Team> teams) { this.teams = teams; }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getSignUpExpiration() {
        return signUpExpiration;
    }

    public void setSignUpExpiration(Date signUpExpiration) {
        this.signUpExpiration = signUpExpiration;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public TournamentType getType() {
        return type;
    }

    public void setType(TournamentType type) {
        this.type = type;
    }

    @Id
    @Column(name = "TournamentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
