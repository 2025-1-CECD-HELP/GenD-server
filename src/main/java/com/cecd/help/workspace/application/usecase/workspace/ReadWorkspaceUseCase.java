package com.cecd.help.workspace.application.usecase.workspace;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.application.dto.workspace.ReadWorkspaceResponseDto;
import java.util.UUID;

@UseCase
public interface ReadWorkspaceUseCase {
    ReadWorkspaceResponseDto execute(Long workspaceId, UUID userId);
}
