package project.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User{

    private long id;

    private String username;
    private String password;

    //private Set<Tournament> tournaments = new HashSet<>();



    public User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "UserId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    @Column(unique = true)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @NotNull
    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password; }

    /*
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    public Set<Tournament> getTournaments() { return tournaments; }
    public void setTournaments(Set<Tournament> tournaments) { this.tournaments = tournaments; }
    */
}
