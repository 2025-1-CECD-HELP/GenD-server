package com.cecd.help.workspace.presentation.request;

import java.util.List;

public record CreateWorkspaceRequestDto(
        String workspaceName,
        String workspaceDescription,
        List<InviteEmail> inviteEmailList
) {
}
