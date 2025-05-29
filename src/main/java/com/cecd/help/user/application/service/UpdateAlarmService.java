package com.cecd.help.user.application.service;

import com.cecd.help.user.application.usecase.UpdateAlarmUseCase;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateAlarmService implements UpdateAlarmUseCase {
    private final UserRepository userRepository;

    @Override
    public Boolean execute(UUID userId) {
        User user = userRepository.findById(userId);
        user.updateAlarm(user.getIsAlarm());
        return true;
    }

}
