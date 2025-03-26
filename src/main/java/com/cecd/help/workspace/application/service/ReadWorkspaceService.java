package com.cecd.help.workspace.application.service;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.dto.ReadWorkspaceResponseDto;
import com.cecd.help.workspace.application.mapper.WorkspaceMapper;
import com.cecd.help.workspace.application.usecase.ReadWorkspaceUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadWorkspaceService implements ReadWorkspaceUseCase {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public ReadWorkspaceResponseDto execute(Long workspaceId, UUID userId) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        Member member = memberRepository.findByUserAndWorkspace(user, workspace);

        return ReadWorkspaceResponseDto.builder()
                .workspaceName(workspace.getWorkspaceName())
                .workspaceDescription(workspace.getWorkspaceDescription())
                .imageUrl(workspace.getWorkspaceImageUrl())
                .workspaceRole(member.getWorkspaceRole())
                .build();

    }

}
