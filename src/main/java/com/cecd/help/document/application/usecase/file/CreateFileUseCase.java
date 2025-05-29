package com.cecd.help.document.application.usecase.file;

import com.cecd.help.core.annotation.UseCase;
import org.springframework.web.multipart.MultipartFile;

@UseCase
public interface CreateFileUseCase {
    Boolean execute(Long dirId, String fileName, MultipartFile file);
}
