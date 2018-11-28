package project.service.Interfaces;

import project.persistence.entities.Match;
import project.persistence.entities.Tournament;

import java.util.List;

public interface IMatchService {
    /**
     * Save a {@link Match}
     * @param match {@link Match} to be saved
     * @return {@link Match} that was saved
     */
    Match save(Match match);

    /**
     * Delete {@link Match}
     * @param match {@link Match} to be deleted
     */
    void delete(Match match);

    /**
     * Find a {@link Match} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Match} with {@link Long id}
     */
    Match findOne(Long id);

    /**
     * Find all {@link Match}s with {@link Long teamId}
     * @param teamId {@link Long}
     * @return All {@link Match}s with the {@link Long tournamentId} passed
     */
    List<Match> findByTeamId(long teamId);

    /**
     * Find all {@link Match}s with {@link Long tournamentId}
     * @param tournamentId {@link Long}
     * @return All {@link Match}s with the {@link Long tournamentId} passed
     */
    List<Match> findByTournamentId(long tournamentId);

    List<Match> generateMatches(Tournament tournament);
}
