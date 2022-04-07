package Spring.test.edu.repositories;

import Spring.test.edu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
