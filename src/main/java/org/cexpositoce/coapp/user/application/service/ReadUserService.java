package org.cexpositoce.coapp.user.application.service;

import org.cexpositoce.coapp.user.application.dto.UserResponse;
import org.cexpositoce.coapp.user.application.exceptions.UserNotFoundException;
import org.cexpositoce.coapp.user.domain.model.User;
import org.cexpositoce.coapp.user.domain.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReadUserService {
    private final UserRepository userRepository;

    public ReadUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }
}
