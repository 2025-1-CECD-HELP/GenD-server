package com.cecd.help.workspace.domain.repository;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    void saveAll(List<Member> members);

    Member findByUserAndWorkspace(User user, Workspace workspace);
}
