package project.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.controller.UserController;
import project.persistence.entities.User;
import project.persistence.repositories.IUserRepository;
import project.service.Interfaces.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {
    private IUserRepository repository;

    // Dependency Injection
    @Autowired
    public UserService(IUserRepository repository) {
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
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public String hashPW(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        return encoder.encode(password);
    }

    public User checkCredentials(String username, String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        User user = findByUsername(username);
        if(user != null && encoder.matches(password, user.getPassword())) return user;
        else return null;
    }
}
