package com.cecd.help.document.domain.repository;

import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository {
    void save(Directory directory);
    Directory findById(Long directoryId);
    List<Directory> findByParIdAndWorkspace(Long parId, Workspace workspace);
    List<Directory> findByWorkspace(Workspace workspace);
    void deleteAll(List<Directory> directory);
    Directory findByIdAndWorkspace(Long directoryId, Workspace workspace);
    Directory findByWorkspaceAndParId(Workspace workspace, Long parId);
}
