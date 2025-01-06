package org.cexpositoce.coapp.login.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginSession {
    private Long id;
    private String username;
    private String password;
    private String role;
    private String refreshToken;
}
