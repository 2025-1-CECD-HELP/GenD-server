package com.cecd.help.document.application.service.file;

import com.cecd.help.document.application.usecase.file.UpdateFileUseCase;
import com.cecd.help.document.domain.entity.Store;
import com.cecd.help.document.domain.repository.StoreRepository;
import com.cecd.help.document.presentation.request.UpdateFileRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateFileService implements UpdateFileUseCase {
    private final StoreRepository storeRepository;

    public Boolean execute(Long documentId, UpdateFileRequestDto updateFileRequestDto) {
        Store store = storeRepository.findById(documentId);

        store.updateFileName(updateFileRequestDto.documentTitle());

        return true;
    }

}
