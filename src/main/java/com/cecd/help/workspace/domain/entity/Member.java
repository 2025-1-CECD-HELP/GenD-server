package com.cecd.help.workspace.domain.entity;

import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.schedule.domain.entity.Schedule;
import com.cecd.help.user.domain.entity.User;
import com.cecd.help.workspace.domain.type.WorkspaceRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "isSchedule")
    private Boolean isSchedule;

    @Column(nullable = false, name = "isPost")
    private Boolean isPost;

    @Column(nullable = false, name = "workspace_role")
    @Enumerated(EnumType.STRING)
    private WorkspaceRole workspaceRole;

    //------------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "workspace_id")
    private Workspace workspace;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts;

    //------------------------------------------------

    @Builder
    public Member(WorkspaceRole workspaceRole, User user, Workspace workspace) {
        this.workspaceRole = workspaceRole;
        this.user = user;
        this.workspace = workspace;
        this.isSchedule = true;
        this.isPost = true;
    }

    public void updateRole() {
        this.workspaceRole = WorkspaceRole.eAdmin;
    }

    public void updateIsAlarm(Boolean isPost, Boolean isSchedule) {
        this.isPost = isPost;
        this.isSchedule = isSchedule;
    }

    public void downRole() {
        this.workspaceRole = WorkspaceRole.eMember;
    }

}
