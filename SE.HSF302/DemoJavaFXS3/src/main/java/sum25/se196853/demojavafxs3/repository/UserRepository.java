package sum25.se196853.demojavafxs3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sum25.se196853.demojavafxs3.entity.User;

public interface UserRepository  extends JpaRepository<User,Integer> {
        User findByUsernameAndPassword(String username, String password);
}
