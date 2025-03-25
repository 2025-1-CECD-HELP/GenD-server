package com.cecd.help.workspace.infrastructure.implement;

import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.repository.MemberRepository;
import com.cecd.help.workspace.infrastructure.jpa.MemberJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void saveAll(List<Member> members) {
        memberJpaRepository.saveAll(members);
    }
}
