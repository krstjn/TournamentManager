package project.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entities.Tournament;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Tournament save(Tournament tournament);

    void delete(Tournament tournament);

    List<Tournament> findAll();

    // If we need a custom query that maybe doesn't fit the naming convention used by the JPA repository,
    // then we can write it quite easily with the @Query notation, like you see below.
    // This method returns all Tournaments where the length of the name is equal or greater than 3 characters.
    @Query(value = "SELECT p FROM Tournament p where length(p.name) >= 3 ")
    List<Tournament> findAllWithNameLongerThan3Chars();

    // Instead of the method findAllReverseOrder() in TournamentService.java,
    // We could have used this method by adding the words
    // ByOrderByIdDesc, which mean: Order By Id in a Descending order
    //
    List<Tournament> findAllByOrderByIdDesc();

    @Query(value = "SELECT p FROM Tournament p WHERE p.id = ?1")
    Tournament findOne(Long id);

    List<Tournament> findByName(String name);
}
