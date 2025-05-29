package com.cecd.help.document.application.usecase.directory;

import com.cecd.help.core.annotation.UseCase;

@UseCase
public interface DeleteDirectoryUseCase {
    Boolean execute(Long dirId);
}
