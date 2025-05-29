package com.cecd.help.workspace.application.service.member;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.mapper.MemberMapper;
import com.cecd.help.workspace.application.usecase.member.CreateMemberUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import com.cecd.help.workspace.presentation.request.InviteEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateMemberService implements CreateMemberUseCase {
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final WorkspaceRepository workspaceRepository;
    private final MemberMapper memberMapper;

    @Override
    public Boolean execute(InviteEmail inviteEmail, Long workspaceId) {
        User newUser = userRepository.findByEmail(inviteEmail.email());

        Workspace workspace = workspaceRepository.findById(workspaceId);

        if(memberRepository.existsByUserAndWorkspaceAndWorkspaceRole(newUser, workspace, WorkspaceRole.eMember))
            throw new CustomException(ErrorCode.NOT_FOUND_USER); // 멤버 이미 존재

        Member newMember = memberMapper.toEntity(WorkspaceRole.eMember, newUser, workspace);

        memberRepository.save(newMember);
        return true;
    }

}
