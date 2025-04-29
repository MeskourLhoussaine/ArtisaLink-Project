package ma.artisanat.user_service.service;




import ma.artisanat.user_service.model.User;
import ma.artisanat.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    public User getById(long id) {
        return userRepository.findById(id).orElse(null);
    }

}
