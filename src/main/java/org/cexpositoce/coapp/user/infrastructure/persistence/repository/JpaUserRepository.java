package org.cexpositoce.coapp.user.infrastructure.persistence.repository;

import org.cexpositoce.coapp.user.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByRefreshToken(String refreshToken);
}