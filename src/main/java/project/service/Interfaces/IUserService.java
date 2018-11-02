package project.service.Interfaces;

import project.persistence.entities.User;

import java.util.List;

public interface IUserService {
    User save(User user);

    /**
     * Delete {@link User}
     * @param user {@link User} to be deleted
     */
    void delete(User user);

    /**
     * Get all {@link User}s
     * @return A list of {@link User}s
     */
    List<User> findAll();

    /**
     * Find a {@link User} based on {@link Long id}
     * @param id {@link Long}
     * @return A {@link User} with {@link long id}
     */
    User findOne(Long id);

    /**
     * Find all {@link User}s with {@link String name}
     * @param username {@link String}
     * @return All {@link User}s with the {@link String name} passed
     */
    List<User> findByUsername(String username);
}
