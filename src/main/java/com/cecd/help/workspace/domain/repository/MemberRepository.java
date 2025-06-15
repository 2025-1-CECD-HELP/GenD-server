package com.cecd.help.workspace.domain.repository;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository {
    void saveAll(List<Member> members);

    Member findByUserAndWorkspace(User user, Workspace workspace);

    boolean existsByUserAndWorkspaceAndWorkspaceRole(User user, Workspace workspace, WorkspaceRole workspaceRole);

    List<Member> findAllByUser(User user);

    List<Member> findAllByWorkspace(Workspace workspace);

    List<Member> findAllByWorkspaceAndIsSchedule(Workspace workspace, Boolean isSchedule);

    List<Member> findAllByWorkspaceAndIsPost(Workspace workspace, Boolean isPost);

    Member findByUser(User user);

    Member findById(Long id);

    void delete(Member member);

    void save(Member member);

}
