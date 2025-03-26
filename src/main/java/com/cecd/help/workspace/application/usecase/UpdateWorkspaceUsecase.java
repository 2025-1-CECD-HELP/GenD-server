package com.cecd.help.workspace.application.usecase;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.application.dto.ReadWorkspaceResponseDto;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.presentation.request.UpdateWorkspaceRequestDto;
import java.util.UUID;

@UseCase
public interface UpdateWorkspaceUsecase {
    void execute(Long workspaceId, UpdateWorkspaceRequestDto updateWorkspaceRequestDto, UUID userId);
}
