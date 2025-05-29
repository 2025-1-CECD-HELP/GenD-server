package com.cecd.help.workspace.application.service.workspace;

import com.cecd.help.core.util.S3Util;
import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.document.domain.repository.DirectoryRepository;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.mapper.MemberMapper;
import com.cecd.help.workspace.application.mapper.WorkspaceMapper;
import com.cecd.help.workspace.application.usecase.workspace.CreateWorkspaceUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import com.cecd.help.workspace.presentation.request.CreateWorkspaceRequestDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateWorkspaceService implements CreateWorkspaceUseCase {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final DirectoryRepository directoryRepository;
    private final WorkspaceMapper workspaceMapper;
    private final MemberMapper memberMapper;
    private final S3Util s3Util;


    @Override
    public void execute(MultipartFile multipartFile, CreateWorkspaceRequestDto workspaceRequestDto, UUID userId) {
        User user = userRepository.findById(userId);
        String url = s3Util.upload(multipartFile);
        Workspace workspace = workspaceMapper.toEntity(
                url,
                workspaceRequestDto.workspaceName(),
                workspaceRequestDto.workspaceDescription()
        );
        workspaceRepository.save(workspace);

        Directory defaultDirectory = Directory.builder()
                .workspace(workspace)
                .dirName(workspace.getWorkspaceName())
                .parId(0L)
                .build();
        directoryRepository.save(defaultDirectory);

        Member adminMember = memberMapper.toEntity(
                WorkspaceRole.eAdmin,
                user,
                workspace
        );

        List<User> inviteUsers =  workspaceRequestDto.inviteEmailList().stream()
                .map(inviteEmail -> userRepository.findByEmail(inviteEmail.email()))
                .toList();

        List<Member> members = inviteUsers.stream()
                .map(inviteUser -> memberMapper.toEntity(WorkspaceRole.eMember, inviteUser, workspace))
                .toList();

        List<Member> allMembers = new ArrayList<>();
        allMembers.add(adminMember);
        allMembers.addAll(members);

        memberRepository.saveAll(allMembers);

    }
}
