package project.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team save(Team team);

    void delete(Team team);

    List<Team> findAll();

    // If we need a custom query that maybe doesn't fit the naming convention used by the JPA repository,
    // then we can write it quite easily with the @Query notation, like you see below.
    // This method returns all Tournaments where the length of the name is equal or greater than 3 characters.
    @Query(value = "SELECT t FROM Team t where length(t.name) >= 3 ")
    List<Team> findAllWithNameLongerThan3Chars();

    // Instead of the method findAllReverseOrder() in TournamentService.java,
    // We could have used this method by adding the words
    // ByOrderByIdDesc, which mean: Order By Id in a Descending order
    //
    List<Team> findAllByOrderByIdDesc();

    @Query(value = "SELECT t FROM Team t WHERE t.id = ?1")
    Team findOne(Long id);

    List<Team> findByName(String name);
}
