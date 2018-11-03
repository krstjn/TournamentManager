package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public String hashPW(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User checkCredentials(String username, String password){
        User user = findByUsername(username);
        if(user != null && BCrypt.checkpw(password, user.getPassword())) return user;
        else return null;
    }
}
