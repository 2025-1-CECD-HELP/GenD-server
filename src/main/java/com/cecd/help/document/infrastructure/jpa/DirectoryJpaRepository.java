package com.cecd.help.document.infrastructure.jpa;

import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryJpaRepository extends JpaRepository<Directory, Long> {
    List<Directory> findByParIdAndWorkspace(Long parId, Workspace workspace);
    List<Directory> findByWorkspace(Workspace workspace);
    Optional<Directory> findByIdAndWorkspace(Long directoryId, Workspace workspace);

    Directory findByWorkspaceAndParId(Workspace workspace, Long parId);

}
