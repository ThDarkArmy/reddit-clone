package tda.darkarmy.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.redditclone.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
