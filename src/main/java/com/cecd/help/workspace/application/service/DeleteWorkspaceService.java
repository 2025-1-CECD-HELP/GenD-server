package com.cecd.help.workspace.application.service;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.usecase.DeleteWorkspaceUseCase;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteWorkspaceService implements DeleteWorkspaceUseCase {
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    private final MemberRepository memberRepository;

    @Override
    public void execute(Long workspaceId, UUID userId) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        if( !memberRepository.existsByUserAndWorkspaceAndWorkspaceRole(user, workspace, WorkspaceRole.eAdmin)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        workspaceRepository.delete(workspace);
    }

}
