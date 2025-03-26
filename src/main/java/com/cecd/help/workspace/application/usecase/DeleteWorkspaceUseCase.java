package com.cecd.help.workspace.application.usecase;

import com.cecd.help.core.annotation.UseCase;
import java.util.UUID;

@UseCase
public interface DeleteWorkspaceUseCase {
    void execute(Long workspaceId, UUID userId);
}
