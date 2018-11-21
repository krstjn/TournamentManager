package project.persistence.entities;

import org.springframework.format.annotation.DateTimeFormat;
import project.utils.TournamentType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "tournaments")
public class Tournament {
    private long id;

    private String name;
    private LocalDateTime created;
    private LocalDateTime signUpExpiration;
    private int maxTeams = 10;
    @Enumerated(EnumType.STRING)
    private TournamentType type = TournamentType.League;
    private int nrOfRounds;
    private Set<Team> teams = new HashSet<>();
    private List<Match> matches = new ArrayList<>();
    private User user;
    private boolean isPublic = true;

    public Tournament() { }

    public Tournament(User user) { this.user = user; }

    public Tournament(String name, LocalDateTime signUpExpiration, int maxTeams, TournamentType type, int nrOfRounds, boolean isPublic) {
        this.name = name;
        this.signUpExpiration = signUpExpiration;
        this.maxTeams = maxTeams;
        this.type = type;
        this.nrOfRounds = nrOfRounds;
        this.isPublic = isPublic;
    }

    @OneToMany(mappedBy = "tournament",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Team> getTeams() { return teams; }
    public void setTeams(Set<Team> teams) { this.teams = teams; }

    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getSignUpExpiration() {
        return signUpExpiration;
    }
    public void setSignUpExpiration(LocalDateTime signUpExpiration) {
        this.signUpExpiration = signUpExpiration;
    }

    public int getMaxTeams() { return maxTeams; }
    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public boolean getIsPublic() { return isPublic; }
    public void setIsPublic(boolean isPublic) { this.isPublic = isPublic; }

    public TournamentType getType() {
        return type;
    }
    public void setType(TournamentType type) {
        this.type = type;
    }

    public int getNrOfRounds() { return nrOfRounds; }
    public void setNrOfRounds(int nrOfRounds) { this.nrOfRounds = nrOfRounds; }

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

    @OneToMany(mappedBy = "tournament",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Match> getMatches() { return matches; }
    public void setMatches(List<Match> matches) { this.matches = matches; }

    @ManyToOne(optional = false)
    @JoinColumn(name = "UserId")
    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }
}
