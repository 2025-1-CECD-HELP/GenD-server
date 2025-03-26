package com.cecd.help.workspace.application.usecase;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.presentation.request.UpdateWorkspaceRequestDto;
import java.util.UUID;

@UseCase
public interface UpdateWorkspaceUseCase {
    void execute(Long workspaceId, UpdateWorkspaceRequestDto updateWorkspaceRequestDto, UUID userId);
}
