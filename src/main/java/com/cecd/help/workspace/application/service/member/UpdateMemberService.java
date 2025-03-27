package com.cecd.help.workspace.application.service.member;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.usecase.member.UpdateMemberUseCase;
import com.cecd.help.workspace.domain.entity.Member;
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
public class UpdateMemberService implements UpdateMemberUseCase {
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;


    @Override
    public void execute(UUID userId, Long memberId) {
        User user = userRepository.findById(userId);

        Member member = memberRepository.findByUser(user);

        if(member.getWorkspaceRole().equals(WorkspaceRole.eAdmin)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        Member updateMember = memberRepository.findById(memberId);
        updateMember.updateRole();

    }
}
