package com.cecd.help.workspace.application.service.member;

import com.cecd.help.workspace.application.usecase.member.UpdateMemberAlarmUseCase;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.presentation.request.UpdateMemberAlarmResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateMemberAlarmService implements UpdateMemberAlarmUseCase {
    private final MemberRepository memberRepository;

    public Boolean execute(UpdateMemberAlarmResponseDto updateMemberAlarmResponseDto) {
        Member member = memberRepository.findById(updateMemberAlarmResponseDto.memberId());

        member.updateIsAlarm(updateMemberAlarmResponseDto.isPost(), updateMemberAlarmResponseDto.isSchedule());

        return true;
    }
}
