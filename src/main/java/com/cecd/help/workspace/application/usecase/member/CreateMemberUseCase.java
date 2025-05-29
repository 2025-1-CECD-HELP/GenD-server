package com.cecd.help.workspace.application.usecase.member;

import com.cecd.help.core.annotation.UseCase;
import com.cecd.help.workspace.presentation.request.InviteEmail;

@UseCase
public interface CreateMemberUseCase {
    Boolean execute(InviteEmail inviteEmail, Long workspaceId);
}
