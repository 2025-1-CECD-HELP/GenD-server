package com.cecd.help.workspace.infrastructure.jpa;

import com.cecd.help.workspace.domain.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceJpaRepository extends JpaRepository<Workspace, Long> {

}
