package com.cecd.help.workspace.application.usecase;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.application.dto.ReadWorkspaceListResponseDto;
import java.util.Map;
import java.util.UUID;

@UseCase
public interface ReadWorkspaceListUseCase {
    ReadWorkspaceListResponseDto execute(UUID userId);
}
