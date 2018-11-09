package project.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entities.Team;
import project.persistence.entities.Tournament;

import java.util.List;

public interface ITeamRepository extends JpaRepository<Team, Long> {
    Team save(Team team);

    void delete(Team team);

    @Query(value = "SELECT t FROM Team t WHERE t.id = ?1")
    Team findOne(Long id);

    List<Team> findByTournamentId(Long tournamentId);
}
