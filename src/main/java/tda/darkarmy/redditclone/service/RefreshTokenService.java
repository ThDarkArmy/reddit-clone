package tda.darkarmy.redditclone.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.model.RefreshToken;
import tda.darkarmy.redditclone.repository.RefreshTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    public void validateRefreshToken(String token) throws SpringRedditException {
        refreshTokenRepository.findByToken(token).orElseThrow(()->new SpringRedditException("Invalid refresh token."));
    }

    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }
}
