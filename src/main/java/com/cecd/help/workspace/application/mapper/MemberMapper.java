package com.cecd.help.workspace.application.mapper;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.type.ELoginProvider;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.type.EWorkspaceRole;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member toEntity(EWorkspaceRole eWorkspaceRole, User user, Workspace workspace) {
        return Member.builder()
                .eWorkspaceRole(eWorkspaceRole)
                .user(user)
                .workspace(workspace)
                .build();
    }
}
