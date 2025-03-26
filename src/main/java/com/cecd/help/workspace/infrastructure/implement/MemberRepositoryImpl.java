package com.cecd.help.workspace.infrastructure.implement;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import com.cecd.help.workspace.infrastructure.jpa.MemberJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void saveAll(List<Member> members) {
        memberJpaRepository.saveAll(members);
    }

    @Override
    public Member findByUserAndWorkspace(User user, Workspace workspace) {
        return memberJpaRepository.findByUserAndWorkspace(user, workspace)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public boolean existsByUserAndWorkspaceAndWorkspaceRole(User user, Workspace workspace, WorkspaceRole workspaceRole) {
        return memberJpaRepository.existsByUserAndWorkspaceAndWorkspaceRole(user, workspace, workspaceRole);
    }

}
