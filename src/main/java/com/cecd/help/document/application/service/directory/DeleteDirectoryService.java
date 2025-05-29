package com.cecd.help.document.application.service.directory;

import com.cecd.help.document.application.usecase.directory.DeleteDirectoryUseCase;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteDirectoryService implements DeleteDirectoryUseCase {
    private final DirectoryRepository directoryRepository;

    @Override
    public Boolean execute(Long dirId) {
        Queue<Long> queue = new LinkedList<>();
        queue.add(dirId);
        Directory directory = directoryRepository.findById(dirId);

        List<Directory> directories = new ArrayList<>();
        directories.add(directory);

        while(!queue.isEmpty()) {
            Long parId = queue.poll();

            List<Directory> childDirs = directoryRepository.findByParIdAndWorkspace(parId, directory.getWorkspace());

            if(childDirs == null) continue;

            directories.addAll(childDirs);
            for (Directory child : Objects.requireNonNull(childDirs)) {
                queue.add(child.getId());
            }
        }

        directoryRepository.deleteAll(directories);


        return true;
    }

}
