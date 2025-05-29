package com.cecd.help.workspace.application.usecase.member;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.presentation.request.UpdateRoleRequestDto;
import java.util.UUID;

@UseCase
public interface UpdateMemberUseCase {
    void execute(UUID userId, Long memberId, Long workspaceId, UpdateRoleRequestDto updateRoleRequestDto);
}
