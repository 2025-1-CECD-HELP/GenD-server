package com.cecd.help.workspace.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WorkspaceRole {
    eMember("Member"),
    eAdmin("Admin");

    private final String eWorkspaceRole;
}
