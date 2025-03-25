package com.cecd.help.workspace.application.usecase;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.presentation.request.CreateWorkspaceRequestDto;
import java.util.UUID;

@UseCase
public interface CreateWorkspaceUseCase {
    void execute(CreateWorkspaceRequestDto createWorkspaceRequestDto, UUID userId);
}
