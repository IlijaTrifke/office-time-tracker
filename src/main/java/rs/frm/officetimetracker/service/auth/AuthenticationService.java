package rs.frm.officetimetracker.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.frm.officetimetracker.domain.User;
import rs.frm.officetimetracker.domain.dto.auth.SignInRequest;
import rs.frm.officetimetracker.domain.dto.auth.JwtAuthenticationResponse;
import rs.frm.officetimetracker.domain.dto.auth.SignUpRequest;
import rs.frm.officetimetracker.domain.enumeration.Role;
import rs.frm.officetimetracker.repository.UserRepository;
import rs.frm.officetimetracker.service.UserService;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public JwtAuthenticationResponse signup(SignUpRequest request) {
        Optional<User> optionalUser = userService.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists. @409");
        }
        var user = User.builder()
                .dateCreated(Instant.now())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

