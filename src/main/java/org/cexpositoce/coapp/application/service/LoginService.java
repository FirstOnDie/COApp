package org.cexpositoce.coapp.application.service;

import org.cexpositoce.coapp.application.dto.LoginRequest;
import org.cexpositoce.coapp.application.dto.LoginResponse;
import org.cexpositoce.coapp.domain.model.User;
import org.cexpositoce.coapp.infrastructure.config.JwtUtil;
import org.cexpositoce.coapp.infrastructure.persistence.JpaUserAdapter;
import org.cexpositoce.coapp.infrastructure.persistence.UserEntity;
import org.cexpositoce.coapp.infrastructure.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final JpaUserAdapter jpaUserAdapter;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, JwtUtil jwtUtil, JpaUserAdapter jpaUserAdapter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.jpaUserAdapter = jpaUserAdapter;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(request.getUsername());

        if (userEntityOpt.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        User user = jpaUserAdapter.toDomain(userEntityOpt.get());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String refreshToken = UUID.randomUUID().toString();

        UserEntity userEntity = userEntityOpt.get();
        userEntity.setRefreshToken(refreshToken);
        userRepository.save(userEntity);

        return new LoginResponse(accessToken, refreshToken);
    }
}