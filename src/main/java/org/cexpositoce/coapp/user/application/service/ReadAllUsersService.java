package org.cexpositoce.coapp.user.application.service;

import org.cexpositoce.coapp.user.application.dto.UserResponse;
import org.cexpositoce.coapp.user.domain.model.User;
import org.cexpositoce.coapp.user.domain.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReadAllUsersService {
    private final UserRepository userRepository;

    public ReadAllUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }
    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getRole());
    }
}
