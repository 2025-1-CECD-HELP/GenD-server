package com.cecd.help.document.application.service.file;

import com.cecd.help.core.util.S3Util;
import com.cecd.help.document.application.usecase.file.CreateFileUseCase;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.entity.Store;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.document.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateFileService implements CreateFileUseCase {
    private final DirectoryRepository directoryRepository;
    private final StoreRepository storeRepository;
    private final S3Util s3Util;

    @Override
    public Boolean execute(Long dirId, String fileName, MultipartFile file) {
        Directory directory = directoryRepository.findById(dirId);

        String url = s3Util.upload(file);

        Store store = Store.builder()
                .directory(directory)
                .fileName(fileName)
                .fileUrl(url)
                .build();

        storeRepository.save(store);

        return true;
    }
}
