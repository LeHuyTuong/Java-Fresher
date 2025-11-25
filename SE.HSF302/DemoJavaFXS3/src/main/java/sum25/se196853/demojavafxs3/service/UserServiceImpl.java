package sum25.se196853.demojavafxs3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se196853.demojavafxs3.entity.User;
import sum25.se196853.demojavafxs3.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public User getUser(String email, String password) {
        return userRepository.findByUsernameAndPassword(email, password); //
    }

    @Override
    public boolean createUser(User user) {
        return userRepository.save(user) != null;
    }

    // *** Thêm triển khai cho phương thức mới ***
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); //
    }
}
