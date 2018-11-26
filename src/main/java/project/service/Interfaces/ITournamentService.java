package project.service.Interfaces;

import project.persistence.entities.Tournament;
import project.persistence.entities.User;
import project.utils.ScoreboardItem;

import java.time.LocalDateTime;
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

    /**
     * Find all tournaments that contain the search String
     * @param search
     * @return
     */
    List<Tournament> findByNameSearch(String search);

    /**
     * Generate the scoreboard based on the matches already played
     * @param tournament
     * @return returns a list containing
     */
    List<ScoreboardItem> generateScoreboard(Tournament tournament);

    /**
     * Creates the tournament
     * @param tournament
     * @param teams the teams participating in the tournament
     * @return Tournament the result of creating the tournament
     */
    Tournament create(Tournament tournament, String[] teams);
}
