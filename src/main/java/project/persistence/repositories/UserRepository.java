package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    void delete(User user);

    List<User> findAll();

    @Query(value = "SELECT p FROM User p WHERE p.id = ?1")
    User findOne(Long id);

    User findByUsername(String username);
}
