package com.cecd.help.workspace.application.service;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.dto.ReadWorkspaceListResponseDto;
import com.cecd.help.workspace.application.dto.ReadWorkspaceResponseDto;
import com.cecd.help.workspace.application.usecase.ReadWorkspaceListUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadWorkspaceListService implements ReadWorkspaceListUseCase {
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    private final MemberRepository memberRepository;


    @Override
    public ReadWorkspaceListResponseDto execute(UUID userId) {
        User user = userRepository.findById(userId);

        List<Member> members = memberRepository.findAllByUser(user);

        List<ReadWorkspaceResponseDto> workspaceList = members.stream()
                .map(this::toWorkspaceResponseDto)
                .toList();

        return ReadWorkspaceListResponseDto.builder()
                .workspaceList(workspaceList)
                .build();

    }

    private ReadWorkspaceResponseDto toWorkspaceResponseDto(Member member) {
        var workspace = member.getWorkspace();

        return ReadWorkspaceResponseDto.builder()
                .workspaceId(workspace.getId())
                .workspaceName(workspace.getWorkspaceName())
                .workspaceDescription(workspace.getWorkspaceDescription())
                .imageUrl(workspace.getWorkspaceImageUrl())
                .build();

    }
}
