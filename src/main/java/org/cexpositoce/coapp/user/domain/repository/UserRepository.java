package org.cexpositoce.coapp.user.domain.repository;

import org.cexpositoce.coapp.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);      // Buscar por nombre de usuario
    Optional<User> findByRefreshToken(String refreshToken); // Buscar por refresh token
    Optional<User> findById(Long id);                   // Buscar por ID
    List<User> findAll();                               // Obtener todos los usuarios
    User save(User user);                               // Guardar o actualizar un usuario
    void deleteById(Long id);                           // Eliminar por ID
    boolean existsById(Long id);                        // Verificar si un usuario existe por ID
}
