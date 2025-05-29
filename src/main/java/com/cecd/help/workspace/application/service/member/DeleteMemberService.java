package com.cecd.help.workspace.application.service.member;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.usecase.member.DeleteMemberUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteMemberService implements DeleteMemberUseCase {
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public void execute(UUID userId, Long memberId, Long workspaceId) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        Member member = memberRepository.findByUserAndWorkspace(user, workspace);

        if (!member.getWorkspaceRole().equals(WorkspaceRole.eAdmin)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        Member deleteMember = memberRepository.findById(memberId);

        memberRepository.delete(deleteMember);
    }
}
