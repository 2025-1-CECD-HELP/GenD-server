package com.cecd.help.workspace.application.service.member;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.dto.member.ReadMemberListResponseDto;
import com.cecd.help.workspace.application.dto.member.ReadMemberResponseDto;
import com.cecd.help.workspace.application.usecase.member.ReadMemberUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadMemberService implements ReadMemberUseCase {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public ReadMemberListResponseDto execute(UUID userId, Long workspaceId) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        List<Member> members = memberRepository.findAllByWorkspace(workspace);

        List<ReadMemberResponseDto> readMemberResponseDtos = members.stream()
                .map(this::toDto)
                .toList();

        return ReadMemberListResponseDto.builder()
                .memberList(readMemberResponseDtos)
                .build();

    }

    private ReadMemberResponseDto toDto(Member member) {
        return ReadMemberResponseDto.builder()
                .memberId(member.getId())
                .memberName(member.getUser().getNickname())
                .memberImage(member.getUser().getProfileImageUrl())
                .memberRole(member.getWorkspaceRole())
                .build();
    }

}
