package com.cecd.help.document.application.service.directory;

import com.cecd.help.document.application.usecase.directory.UpdateDirectoryUseCase;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.document.presentation.request.DirectoryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateDirectoryService implements UpdateDirectoryUseCase {
    private final DirectoryRepository directoryRepository;


    @Override
    public Boolean execute(DirectoryRequestDto directoryRequestDto, Long dirId) {
        Directory directory = directoryRepository.findById(dirId);
        directory.updateDirName(directoryRequestDto.directoryName());
        

        return true;
    }
}
