package th.ac.ku.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import th.ac.ku.book.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
