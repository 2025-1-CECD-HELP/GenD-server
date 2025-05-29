package com.cecd.help.workspace.application.service.workspace;

import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.dto.workspace.ReadWorkspaceListResponseDto;
import com.cecd.help.workspace.application.dto.workspace.ReadWorkspaceResponseDto;
import com.cecd.help.workspace.application.usecase.workspace.ReadWorkspaceListUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
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
    private final DirectoryRepository directoryRepository;


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
        Directory directory = directoryRepository.findByWorkspaceAndParId(workspace, 0L);
        return ReadWorkspaceResponseDto.builder()
                .workspaceId(workspace.getId())
                .workspaceName(workspace.getWorkspaceName())
                .workspaceDescription(workspace.getWorkspaceDescription())
                .imageUrl(workspace.getWorkspaceImageUrl())
                .workspaceRole(member.getWorkspaceRole())
                .rootDirId(directory.getId())
                .isPost(member.getIsPost())
                .isSchedule(member.getIsSchedule())
                .build();

    }
}
