package org.cexpositoce.coapp.login.interfaces.rest;

import org.cexpositoce.coapp.login.application.dto.RefreshTokenRequest;
import org.cexpositoce.coapp.login.application.dto.RefreshTokenResponse;
import org.cexpositoce.coapp.login.application.service.LoginService;
import org.cexpositoce.coapp.login.application.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        RefreshTokenResponse response = refreshTokenService.refreshAccessToken(request);
        return ResponseEntity.ok(response);
    }
}
