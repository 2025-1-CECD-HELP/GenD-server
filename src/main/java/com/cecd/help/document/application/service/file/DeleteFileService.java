package com.cecd.help.document.application.service.file;

import com.cecd.help.document.application.usecase.file.DeleteFileUseCase;
import com.cecd.help.document.domain.entity.Store;
import com.cecd.help.document.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteFileService implements DeleteFileUseCase {
    private final StoreRepository storeRepository;

    public Boolean execute(Long documentId) {
        Store store = storeRepository.findById(documentId);

        storeRepository.delete(store);
        return true;
    }
}
