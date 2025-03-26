package com.cecd.help.workspace.application.dto;

import com.cecd.help.workspace.domain.type.EWorkspaceRole;
import lombok.Builder;

@Builder
public record ReadWorkspaceResponseDto(
        String workspaceName,
        String workspaceDescription,
        String imageUrl,
        EWorkspaceRole eWorkspaceRole
) {
}
