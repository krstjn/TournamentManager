package project.persistence.entities;

import org.springframework.format.annotation.DateTimeFormat;
import project.utils.TournamentType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date created = new Date();
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date signUpExpiration;
    private int teamCount = 0;
    private int maxTeams = 10;
    @Enumerated(EnumType.STRING)
    private TournamentType type = TournamentType.GroupStage;


    public Tournament() {
    }
    public Tournament(String name, Date signUpExpiration, int teamCount, int maxTeams, TournamentType type) {
        this.name = name;
        this.signUpExpiration = signUpExpiration;
        this.teamCount = teamCount;
        this.maxTeams = maxTeams;
        this.type = type;
    }
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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
