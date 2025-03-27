package com.cecd.help.workspace.application.usecase.workspace;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.application.dto.workspace.ReadWorkspaceListResponseDto;
import java.util.UUID;

@UseCase
public interface ReadWorkspaceListUseCase {
    ReadWorkspaceListResponseDto execute(UUID userId);
}
