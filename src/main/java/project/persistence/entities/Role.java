package project.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    private Long id;

    private User user;
    private String role;

    public Role(){}

    public Role(User user, String role) {
        this.user = user;
        this.role = role;
    }

    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
