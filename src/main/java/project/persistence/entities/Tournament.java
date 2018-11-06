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
    private int maxTeams = 10;
    @Enumerated(EnumType.STRING)
    private TournamentType type = TournamentType.League;
    private Set<Team> teams = new HashSet<>();
    //private User owner;
    private boolean isPublic = true;

    public Tournament() {
    }
    public Tournament(String name, Date signUpExpiration, int maxTeams, TournamentType type, boolean isPublic/*, User user*/) {
        this.name = name;
        this.signUpExpiration = signUpExpiration;
        this.maxTeams = maxTeams;
        this.type = type;
        this.isPublic = isPublic;
        //this.owner = user;
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

    /*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ownerId")
    public User getOwner() { return this.owner; }
    public void setOwner(User owner) { this.owner = owner; }
     */
}
