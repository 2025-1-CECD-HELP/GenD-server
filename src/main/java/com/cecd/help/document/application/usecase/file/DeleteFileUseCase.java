package com.cecd.help.document.application.usecase.file;

import com.cecd.help.core.annotation.UseCase;

@UseCase
public interface DeleteFileUseCase {
    Boolean execute(Long documentId);
}
