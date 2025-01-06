package org.cexpositoce.coapp.interfaces.rest;

import org.cexpositoce.coapp.application.dto.LoginResponse;
import org.cexpositoce.coapp.infrastructure.config.JwtUtil;
import org.cexpositoce.coapp.infrastructure.persistence.UserEntity;
import org.cexpositoce.coapp.infrastructure.persistence.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/refresh-token")
    public LoginResponse refreshAccessToken(@RequestBody String refreshToken) {
        Optional<UserEntity> userEntityOpt = userRepository.findByRefreshToken(refreshToken);

        if (userEntityOpt.isEmpty()) {
            throw new RuntimeException("Invalid refresh token");
        }

        UserEntity userEntity = userEntityOpt.get();
        String newAccessToken = jwtUtil.generateToken(userEntity.getUsername(), userEntity.getRole());

        String newRefreshToken = UUID.randomUUID().toString();
        userEntity.setRefreshToken(newRefreshToken);
        userRepository.save(userEntity);

        return new LoginResponse(newAccessToken, newRefreshToken);
    }
}
