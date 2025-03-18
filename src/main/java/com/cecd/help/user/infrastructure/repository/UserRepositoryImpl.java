package com.cecd.help.user.infrastructure.repository;

import com.cecd.help.user.domain.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<User, UUID> {
}
