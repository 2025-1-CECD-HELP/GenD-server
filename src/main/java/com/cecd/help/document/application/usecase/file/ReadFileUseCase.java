package com.cecd.help.document.application.usecase.file;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.document.application.dto.file.ReadFileListResponseDto;

@UseCase
public interface ReadFileUseCase {
    ReadFileListResponseDto execute(Long workspaceId, Long dirId);
}
