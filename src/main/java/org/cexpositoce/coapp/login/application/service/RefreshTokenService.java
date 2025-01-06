package org.cexpositoce.coapp.login.application.service;

import org.cexpositoce.coapp.login.application.dto.LoginResponse;
import org.cexpositoce.coapp.login.application.dto.RefreshTokenRequest;
import org.cexpositoce.coapp.login.application.dto.RefreshTokenResponse;
import org.cexpositoce.coapp.login.application.exceptions.InvalidLoginException;
import org.cexpositoce.coapp.login.infrastructure.security.JwtUtil;
import org.cexpositoce.coapp.user.domain.model.User;
import org.cexpositoce.coapp.user.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public RefreshTokenService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public RefreshTokenResponse refreshAccessToken(RefreshTokenRequest request) {
        User user = userRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        String newAccessToken = jwtUtil.generateToken(user.getUsername(), user.getRole());
        String newRefreshToken = UUID.randomUUID().toString();

        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        return new RefreshTokenResponse(newAccessToken, newRefreshToken);
    }
}