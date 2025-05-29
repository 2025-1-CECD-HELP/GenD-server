package com.cecd.help.workspace.application.service.workspace;

import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.dto.workspace.ReadWorkspaceResponseDto;
import com.cecd.help.workspace.application.mapper.WorkspaceMapper;
import com.cecd.help.workspace.application.usecase.workspace.ReadWorkspaceUseCase;
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
    private final DirectoryRepository directoryRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public ReadWorkspaceResponseDto execute(Long workspaceId, UUID userId) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        Directory directory = directoryRepository.findByWorkspaceAndParId(workspace, 0L);

        Member member = memberRepository.findByUserAndWorkspace(user, workspace);

        return ReadWorkspaceResponseDto.builder()
                .workspaceId(workspace.getId())
                .workspaceName(workspace.getWorkspaceName())
                .workspaceDescription(workspace.getWorkspaceDescription())
                .imageUrl(workspace.getWorkspaceImageUrl())
                .workspaceRole(member.getWorkspaceRole())
                .rootDirId(directory.getId())
                .build();

    }

}
