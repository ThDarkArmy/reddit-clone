package tda.darkarmy.redditclone.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.redditclone.dto.AuthenticationResponse;
import tda.darkarmy.redditclone.dto.LoginRequest;
import tda.darkarmy.redditclone.dto.RefreshTokenRequest;
import tda.darkarmy.redditclone.dto.RegisterRequest;
import tda.darkarmy.redditclone.exception.SpringRedditException;
import tda.darkarmy.redditclone.model.RefreshToken;
import tda.darkarmy.redditclone.service.AuthService;
import tda.darkarmy.redditclone.service.RefreshTokenService;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterRequest registerRequest) throws SpringRedditException {
        String token = authService.signup(registerRequest);
        return status(HttpStatus.OK).body("User registration successful, token: "+token);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<?> verifyAccount(@PathVariable String token) throws SpringRedditException {
        authService.verifyAccount(token);
        return status(HttpStatus.OK).body("Account activated!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws SpringRedditException {
        AuthenticationResponse authenticationResponse = authService.login(loginRequest);
        return status(HttpStatus.OK).body(authenticationResponse);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws SpringRedditException {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshToken refreshToken){
        refreshTokenService.deleteRefreshToken(refreshToken.getToken());
        return status(HttpStatus.OK).body("Refresh token deleted successfully!");
    }


}
