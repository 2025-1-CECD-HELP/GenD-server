package com.cecd.help.user.infrastructure.repository;

import com.cecd.help.user.domain.entity.User;
import java.util.Optional;

public interface UserRepository {

    User findByLoginId(String loginId);

}
