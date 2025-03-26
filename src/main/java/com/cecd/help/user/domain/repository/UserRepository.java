package com.cecd.help.user.domain.repository;

import com.cecd.help.user.domain.entity.User;
import java.util.UUID;

public interface UserRepository {

    User findByLoginId(String loginId);

    User findById(UUID id);

}
