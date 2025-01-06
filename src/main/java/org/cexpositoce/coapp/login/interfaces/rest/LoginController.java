package org.cexpositoce.coapp.login.interfaces.rest;

import org.cexpositoce.coapp.login.application.dto.LoginRequest;
import org.cexpositoce.coapp.login.application.dto.LoginResponse;
import org.cexpositoce.coapp.login.application.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = loginService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}
