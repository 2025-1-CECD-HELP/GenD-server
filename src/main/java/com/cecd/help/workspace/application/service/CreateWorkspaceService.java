package com.cecd.help.workspace.application.service;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.mapper.MemberMapper;
import com.cecd.help.workspace.application.mapper.WorkspaceMapper;
import com.cecd.help.workspace.application.usecase.CreateWorkspaceUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.domain.type.EWorkspaceRole;
import com.cecd.help.workspace.presentation.request.CreateWorkspaceRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateWorkspaceService implements CreateWorkspaceUseCase {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final WorkspaceMapper workspaceMapper;
    private final MemberMapper memberMapper;


    @Override
    public void execute(CreateWorkspaceRequestDto workspaceRequestDto, UUID userId) {
        User user = userRepository.findById(userId);

        Workspace workspace = workspaceMapper.toEntity(
                workspaceRequestDto.workspaceImageUrl(),
                workspaceRequestDto.workspaceName(),
                workspaceRequestDto.workspaceDescription()
        );
        workspaceRepository.save(workspace);

        Member adminMember = memberMapper.toEntity(
                EWorkspaceRole.Admin,
                user,
                workspace
        );

        List<User> inviteUsers =  workspaceRequestDto.inviteEmailList().stream()
                .map(inviteEmail -> userRepository.findByLoginId(inviteEmail.email()))
                .toList();

        List<Member> members = inviteUsers.stream()
                .map(inviteUser -> memberMapper.toEntity(EWorkspaceRole.Member, inviteUser, workspace))
                .toList();

        List<Member> allMembers = new ArrayList<>();
        allMembers.add(adminMember);
        allMembers.addAll(members);

        memberRepository.saveAll(allMembers);

    }
}
