package com.cecd.help.workspace.application.service.member;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.dto.member.ReadMemberInfoResponseDto;
import com.cecd.help.workspace.application.usecase.member.ReadMemberInfoUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadMemberInfoService implements ReadMemberInfoUseCase {
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public ReadMemberInfoResponseDto execute(Long workspaceId, UUID userId) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        Member member = memberRepository.findByUserAndWorkspace(user, workspace);

        return ReadMemberInfoResponseDto.builder()
                .memberId(member.getId())
                .memberName(member.getUser().getNickname())
                .loginProvider(member.getUser().getProvider().name())
                .memberEmail(member.getUser().getEmail())
                .build();
    }
}
