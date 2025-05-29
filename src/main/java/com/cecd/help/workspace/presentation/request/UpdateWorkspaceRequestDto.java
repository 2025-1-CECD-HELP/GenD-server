package com.cecd.help.workspace.presentation.request;

public record UpdateWorkspaceRequestDto(
        String workspaceName,
        String workspaceDescription
) {
}
