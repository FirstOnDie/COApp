package org.cexpositoce.coapp.user.application.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String role;
}
