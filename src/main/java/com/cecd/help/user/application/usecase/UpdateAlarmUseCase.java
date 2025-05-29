package com.cecd.help.user.application.usecase;

import com.cecd.help.core.annotation.UseCase;
import java.util.UUID;

@UseCase
public interface UpdateAlarmUseCase {
    Boolean execute(UUID userId);
}
