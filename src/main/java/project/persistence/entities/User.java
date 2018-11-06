package project.persistence.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable{

    private Long id;

    private String username;
    private String password;

    private Set<Role> roles = new HashSet<>();
    private boolean enabled = true;

    private Set<Tournament> tournaments = new HashSet<>();



    public User(){}
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "UserId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @NaturalId
    @Column(name="username", unique = true)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @NotNull
    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password; }

    public boolean isEnabled() {return enabled; }
    public void setEnabled(boolean enabled) {this.enabled = enabled; }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

    @OneToMany(mappedBy ="user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Set<Tournament> getTournaments() { return tournaments; }
    public void setTournaments(Set<Tournament> tournaments) { this.tournaments = tournaments; }
}
