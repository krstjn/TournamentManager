package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Role;
import project.persistence.repositories.RoleRepository;
import project.service.Interfaces.IRoleService;

@Service
public class RoleService implements IRoleService {

    RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role save(Role role){ return repository.save(role); }

    @Override
    public void delete(Role role){ repository.delete(role); }
}
