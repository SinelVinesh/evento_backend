package mg.sinel.evento.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sinel.evento.http.requests.AuthRequest;
import mg.sinel.evento.http.responses.AuthResponse;
import mg.sinel.evento.models.Token;
import mg.sinel.evento.models.user.User;
import mg.sinel.evento.repositories.TokenRepo;
import mg.sinel.evento.repositories.UserRepo;
import mg.sinel.evento.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAuthService {
    private final UserRepo userRepo;
    private final TokenRepo tokenRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = findByUsername(request.getUsername());
        var token = jwtTokenUtil.generateToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, token);
        return AuthResponse
                .builder()
                .accessToken(token)
                .role(user.getRole())
                .username(user.getUsername())
                .userId(user.getId())
                .build();
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow();
    }

    private void saveUserToken(User user, String token) {
        var tokenToSave = new Token(user, token);
        tokenRepo.save(tokenToSave);
    }

    @Transactional
    public void revokeAllUserTokens(User user) {
        tokenRepo.deleteByUserId(user.getId());
    }
}
