package com.cecd.help.workspace.application.usecase.member;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.application.dto.member.ReadMemberListResponseDto;
import java.util.List;
import java.util.UUID;

@UseCase
public interface ReadMemberUseCase {
    ReadMemberListResponseDto execute(UUID userId, Long workspaceId);
}
