package org.cexpositoce.coapp.user.interfaces.rest;

import org.cexpositoce.coapp.common.aop.interfaces.IsSelfOrAdmin;
import org.cexpositoce.coapp.user.application.dto.UserResponse;
import org.cexpositoce.coapp.user.application.service.ReadUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ReadUserController {

    private final ReadUserService readUserService;

    public ReadUserController(ReadUserService readUserService) {
        this.readUserService = readUserService;
    }


    @IsSelfOrAdmin
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(readUserService.getUserById(id));
    }
}
