package project.service;

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
     * Get all {@link Team}s
     * @return A list of {@link Team}s
     */
    List<Team> findAll();

    /**
     * Get all {@link Team}s in a reverse order
     * @return A reversed list of {@link Team}
     */
    List<Team> findAllReverseOrder();

    List<Team> findAllByDateReverseOrder();

    /**
     * Find a {@link Team} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Team} with {@link Long id}
     */
    Team findOne(Long id);

    /**
     * Find all {@link Team}s with {@link String name}
     * @param name {@link String}
     * @return All {@link Team}s with the {@link String name} passed
     */
    List<Team> findByName(String name);
}
