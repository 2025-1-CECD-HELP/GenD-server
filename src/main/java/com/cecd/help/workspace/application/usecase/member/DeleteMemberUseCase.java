package com.cecd.help.workspace.application.usecase.member;

import com.cecd.help.core.annotation.UseCase;
import java.util.UUID;

@UseCase
public interface DeleteMemberUseCase {
    void execute(UUID userId, Long memberId, Long workspaceId);
}
