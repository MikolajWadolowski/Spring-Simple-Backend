package Spring.test.edu.services;

import Spring.test.edu.models.User;
import Spring.test.edu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public User saveUser(User user) {
        try {
             return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

}
