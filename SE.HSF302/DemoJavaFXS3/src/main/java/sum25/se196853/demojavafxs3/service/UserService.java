package sum25.se196853.demojavafxs3.service;

import sum25.se196853.demojavafxs3.entity.User;

import java.util.List;

public interface UserService  {
    User getUser(String email, String password);
    boolean createUser(User user);
    List<User> getAllUsers();
}
