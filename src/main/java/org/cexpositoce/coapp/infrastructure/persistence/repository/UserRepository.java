package org.cexpositoce.coapp.infrastructure.persistence.repository;

import org.cexpositoce.coapp.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByRefreshToken(String refreshToken);
}
