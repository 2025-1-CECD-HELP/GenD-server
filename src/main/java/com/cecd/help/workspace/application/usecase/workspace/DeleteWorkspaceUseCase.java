package com.cecd.help.workspace.application.usecase.workspace;

import com.cecd.help.core.annotation.UseCase;
import java.util.UUID;

@UseCase
public interface DeleteWorkspaceUseCase {
    void execute(Long workspaceId, UUID userId);
}
