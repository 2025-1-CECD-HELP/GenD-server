package com.cecd.help.workspace.application.usecase.member;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.presentation.request.UpdateMemberAlarmResponseDto;

@UseCase
public interface UpdateMemberAlarmUseCase {
    Boolean execute(UpdateMemberAlarmResponseDto updateMemberAlarmResponseDto);
}
