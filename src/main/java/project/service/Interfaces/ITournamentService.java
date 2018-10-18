package project.service.Interfaces;

import project.persistence.entities.Tournament;

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
    List<Tournament> findAllReverseOrder();

    List<Tournament> findAllByDateReverseOrder();

    /**
     * Find a {@link Tournament} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link Tournament} with {@link Long id}
     */
    Tournament findOne(Long id);

    /**
     * Find all {@link Tournament}s with {@link String name}
     * @param name {@link String}
     * @return All {@link Tournament}s with the {@link String name} passed
     */
    List<Tournament> findByName(String name);
}
