package project.service.Interfaces;

import project.persistence.entities.Team;

import java.util.List;

public interface ITeamService {
    /**
     * Save a {@link Team}
     * @param team {@link Team} to be saved
     * @return {@link Team} that was saved
     */
    Team save(Team team);

    /**
     * Delete {@link Team}
     * @param team {@link Team} to be deleted
     */
    void delete(Team team);

    /**
     * Find a {@link Team} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Team} with {@link Long id}
     */
    Team findOne(Long id);

    /**
     * Find all {@link Team}s with {@link Long tournamentId}
     * @param tournamentId {@link Long}
     * @return All {@link Team}s with the {@link Long tournamentId} passed
     */
    List<Team> findByTournamentId(Long tournamentId);
}
