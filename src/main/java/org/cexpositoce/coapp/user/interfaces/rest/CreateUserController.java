package org.cexpositoce.coapp.user.interfaces.rest;

import org.cexpositoce.coapp.common.aop.interfaces.IsAdmin;
import org.cexpositoce.coapp.user.application.dto.UserRequest;
import org.cexpositoce.coapp.user.application.dto.UserResponse;
import org.cexpositoce.coapp.user.application.service.CreateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class CreateUserController{

    private final CreateUserService createUserService;

    public CreateUserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @IsAdmin
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse response = createUserService.createUser(request);
        return ResponseEntity.ok(response);
    }
}
