package com.cecd.help.workspace.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EWorkspaceRole {
    Member("Member"),
    Admin("Admin");

    private final String eWorkspaceRole;
}
