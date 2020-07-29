package tda.darkarmy.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tda.darkarmy.redditclone.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
