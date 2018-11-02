package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.User;
import project.persistence.repositories.UserRepository;
import project.service.Interfaces.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    UserRepository repository;

    // Dependency Injection
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
