package org.cexpositoce.coapp.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cexpositoce.coapp.user.application.dto.UserResponse;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String refreshToken;
}
