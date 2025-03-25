package com.cecd.help.workspace.application.mapper;

import com.cecd.help.workspace.domain.entity.Workspace;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper {
    public Workspace toEntity(String workspaceImageUrl, String workspaceName, String workspaceDescription) {
        return Workspace.builder()
                .workspaceImageUrl(workspaceImageUrl)
                .workspaceName(workspaceName)
                .workspaceDescription(workspaceDescription)
                .createAt(LocalDateTime.now())
                .build();
    }
}
