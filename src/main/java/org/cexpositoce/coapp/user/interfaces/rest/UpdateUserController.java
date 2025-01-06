package org.cexpositoce.coapp.user.interfaces.rest;

import org.cexpositoce.coapp.common.aop.interfaces.IsSelfOrAdmin;
import org.cexpositoce.coapp.user.application.dto.UserRequest;
import org.cexpositoce.coapp.user.application.dto.UserResponse;
import org.cexpositoce.coapp.user.application.service.UpdateUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UpdateUserController {
    private final UpdateUserService updateUserService;

    public UpdateUserController(UpdateUserService updateUserService) {
        this.updateUserService = updateUserService;
    }

    @IsSelfOrAdmin
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(updateUserService.updateUser(id, request));
    }
}
