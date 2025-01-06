package org.cexpositoce.coapp.user.interfaces.rest;

import org.cexpositoce.coapp.common.aop.interfaces.IsAdmin;
import org.cexpositoce.coapp.user.application.dto.UserResponse;
import org.cexpositoce.coapp.user.application.service.ReadAllUsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class ReadAllUsersController {

    private final ReadAllUsersService readAllUsersService;

    public ReadAllUsersController(ReadAllUsersService readAllUsersService) {

        this.readAllUsersService = readAllUsersService;
    }

    @IsAdmin
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(readAllUsersService.getAllUsers());
    }
}
