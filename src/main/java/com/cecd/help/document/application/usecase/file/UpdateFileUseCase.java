package com.cecd.help.document.application.usecase.file;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.document.presentation.request.UpdateFileRequestDto;

@UseCase
public interface UpdateFileUseCase {
    Boolean execute(Long documentId, UpdateFileRequestDto updateFileRequestDto);
}
