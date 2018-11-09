package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.entities.Match;

import java.util.List;

public interface IMatchRepository extends JpaRepository<Match, Long> {
    Match save(Match match);

    void delete(Match match);

    Match findOne(long id);

    List<Match> findByTeamId(long teamId);

    List<Match> findByTournamentId(long tournamentId);

}
