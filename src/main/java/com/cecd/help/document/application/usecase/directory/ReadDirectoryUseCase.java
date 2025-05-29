package com.cecd.help.document.application.usecase.directory;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.document.application.dto.directory.ReadDirectoryListResponseDto;

@UseCase
public interface ReadDirectoryUseCase {
    ReadDirectoryListResponseDto execute(Long workspaceId);
}
