package tda.darkarmy.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.redditclone.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
