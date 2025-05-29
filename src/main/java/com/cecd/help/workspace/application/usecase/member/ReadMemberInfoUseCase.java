package com.cecd.help.workspace.application.usecase.member;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.application.dto.member.ReadMemberInfoResponseDto;
import java.util.UUID;

@UseCase
public interface ReadMemberInfoUseCase {
    ReadMemberInfoResponseDto execute(Long workspaceId, UUID userID);
}
