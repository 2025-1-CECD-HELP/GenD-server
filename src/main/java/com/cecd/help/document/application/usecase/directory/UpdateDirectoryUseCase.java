package com.cecd.help.document.application.usecase.directory;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.document.presentation.request.DirectoryRequestDto;

@UseCase
public interface UpdateDirectoryUseCase {
    Boolean execute(DirectoryRequestDto directoryRequestDto, Long dirId);
}
