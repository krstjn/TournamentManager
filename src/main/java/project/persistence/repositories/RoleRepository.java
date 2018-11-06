package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entities.Role;

/************************
 * Höfundur: Kristján P.*
 ************************/
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role save(Role role);

    void delete(Role role);
}
