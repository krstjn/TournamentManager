package project.service.Interfaces;

import project.persistence.entities.Tournament;

import java.util.ArrayList;
import java.util.List;

public interface ITournamentService {
    /**
     * Save a {@link Tournament}
     * @param tournament {@link Tournament} to be saved
     * @return {@link Tournament} that was saved
     */
    Tournament save(Tournament tournament);

    /**
     * Delete {@link Tournament}
     * @param tournament {@link Tournament} to be deleted
     */
    void delete(Tournament tournament);

    /**
     * Get all {@link Tournament}s
     * @return A list of {@link Tournament}s
     */
    List<Tournament> findAll();

    /**
     * Get all {@link Tournament}s in a reverse order
     * @return A reversed list of {@link Tournament}s
     */
    List<Tournament> findAllByOrderByIdDesc();

    /**
     * Find a {@link Tournament} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Tournament} with {@link Long id}
     */
    Tournament findOne(Long id);

    /**
     * Find all {@link Tournament}s with {@link Long userId}
     * @param userId {@link Long}
     * @return All {@link Tournament}s with the {@link Long userId} passed
     */
    List<Tournament> findByUserId(Long userId);


    List generateScoreboard(Tournament tournament);
}
