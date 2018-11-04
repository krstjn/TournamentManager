package project.service.Interfaces;

import project.persistence.entities.Role;

public interface IRoleService {
    /**
     * Save a {@link Role}
     * @param role {@link Role} to be saved
     * @return {@link Role} that was saved
     */
    Role save(Role role);

    /**
     * Delete {@link Role}
     * @param role {@link Role} to be deleted
     */
    void delete(Role role);
}
