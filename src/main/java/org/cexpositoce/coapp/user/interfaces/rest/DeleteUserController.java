package org.cexpositoce.coapp.user.interfaces.rest;

import org.cexpositoce.coapp.common.aop.interfaces.IsAdmin;
import org.cexpositoce.coapp.user.application.service.DeleteUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class DeleteUserController {

    private final DeleteUserService deleteUserService;

    public DeleteUserController(DeleteUserService deleteUserService) {
        this.deleteUserService = deleteUserService;
    }

    @IsAdmin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        deleteUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
