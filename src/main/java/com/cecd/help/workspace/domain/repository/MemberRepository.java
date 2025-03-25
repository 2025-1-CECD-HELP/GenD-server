package com.cecd.help.workspace.domain.repository;

import com.cecd.help.workspace.domain.entity.Member;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    void saveAll(List<Member> members);
}
