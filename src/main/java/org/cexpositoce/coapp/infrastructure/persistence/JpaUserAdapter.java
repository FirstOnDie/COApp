package org.cexpositoce.coapp.infrastructure.persistence;

import org.cexpositoce.coapp.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class JpaUserAdapter {

    public User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getRole(), entity.getRefreshToken());
    }

    public UserEntity toEntity(User domain) {
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());  // Si el ID es generado, podrías omitir esto
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setRole(domain.getRole());
        entity.setRefreshToken(domain.getRefreshToken()); // Mapear el refreshToken
        return entity;
    }
}
