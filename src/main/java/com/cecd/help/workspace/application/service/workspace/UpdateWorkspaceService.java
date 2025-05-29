package com.cecd.help.workspace.application.service.workspace;

import com.cecd.help.core.exception.CustomException;
import com.cecd.help.core.exception.ErrorCode;
import com.cecd.help.core.util.S3Util;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.user.domain.repository.UserRepository;
import com.cecd.help.workspace.application.usecase.workspace.UpdateWorkspaceUseCase;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.domain.repository.WorkspaceRepository;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import com.cecd.help.workspace.presentation.request.UpdateWorkspaceRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateWorkspaceService implements UpdateWorkspaceUseCase {
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    private final MemberRepository memberRepository;
    private final S3Util s3Util;


    @Override
    public void execute(Long workspaceId, MultipartFile multipartFile, UpdateWorkspaceRequestDto updateWorkspaceRequestDto, UUID userId) {
        User user = userRepository.findById(userId);
        Workspace workspace = workspaceRepository.findById(workspaceId);

        if( !memberRepository.existsByUserAndWorkspaceAndWorkspaceRole(user, workspace, WorkspaceRole.eAdmin)) {
            throw new CustomException(ErrorCode.NOT_FOUND_USER);
        }

        if(!multipartFile.isEmpty()) {
            String url = s3Util.upload(multipartFile);
            workspace.updateImageWorkspace(
                    updateWorkspaceRequestDto.workspaceName(),
                    updateWorkspaceRequestDto.workspaceDescription(),
                    url
            );
        } else {
            workspace.updateWorkspace(
                    updateWorkspaceRequestDto.workspaceName(),
                    updateWorkspaceRequestDto.workspaceDescription()
            );
        }



        workspaceRepository.save(workspace);

    }
}
