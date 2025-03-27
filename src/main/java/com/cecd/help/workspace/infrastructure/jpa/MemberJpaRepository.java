package com.cecd.help.workspace.infrastructure.jpa;

import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserAndWorkspace(User user, Workspace workspace);

    boolean existsByUserAndWorkspaceAndWorkspaceRole(
            User user,
            Workspace workspace,
            WorkspaceRole workspaceRole
    );

    List<Member> findAllByUser(User user);

    List<Member> findAllByWorkspace(Workspace workspace);

    Optional<Member> findByUser(User user);
}
