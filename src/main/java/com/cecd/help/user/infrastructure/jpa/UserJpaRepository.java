package com.cecd.help.user.infrastructure.jpa;

import com.cecd.help.user.domain.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLoginId(String loginId);
}
