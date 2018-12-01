package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entities.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role save(Role role);

    void delete(Role role);
}
