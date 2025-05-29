package com.cecd.help.workspace.application.service.member;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.usecase.member.UpdateMemberUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import com.cecd.help.workspace.presentation.request.UpdateRoleRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateMemberService implements UpdateMemberUseCase {
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final WorkspaceRepository workspaceRepository;


    @Override
    public void execute(UUID userId, Long memberId, Long workspaceId, UpdateRoleRequestDto updateRoleRequestDto) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceRepository.findById(workspaceId);

        Member member = memberRepository.findByUserAndWorkspace(user,workspace);

        if(!member.getWorkspaceRole().equals(WorkspaceRole.eAdmin)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER); // admin 아님
        }



        Member updateMember = memberRepository.findById(memberId);
        if (updateRoleRequestDto.isAdmin()) {
            updateMember.updateRole();
        } else {
            updateMember.downRole();
        }


    }
}
