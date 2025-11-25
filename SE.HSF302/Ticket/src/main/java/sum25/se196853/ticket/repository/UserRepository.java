package sum25.se196853.ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sum25.se196853.ticket.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
