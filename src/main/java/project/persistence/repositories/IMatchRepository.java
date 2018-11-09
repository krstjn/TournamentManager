package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Match;

import java.util.List;

public interface IMatchRepository extends JpaRepository<Match, Long> {
    Match save(Match match);

    void delete(Match match);

    @Query(value = "SELECT m FROM Match m WHERE m.id = ?1")
    Match findOne(Long id);

    @Query(value = "SELECT m FROM Match m WHERE m.homeTeam = ?1 OR m.awayTeam = ?1")
    List<Match> findByTeamId(Long teamId);

    @Query
    List<Match> findByTournamentId(Long tournamentId);

}
