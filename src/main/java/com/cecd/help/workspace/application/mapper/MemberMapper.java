package com.cecd.help.workspace.application.mapper;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toEntity(WorkspaceRole workspaceRole, User user, Workspace workspace) {
        return Member.builder()
                .workspaceRole(workspaceRole)
                .user(user)
                .workspace(workspace)
                .build();
    }
}
