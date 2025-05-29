package com.cecd.help.workspace.domain.entity;

import com.cecd.help.document.domain.entity.Directory;
import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.schedule.domain.entity.Schedule;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "workspace_name")
    private String workspaceName;

    @Column(nullable = false, name = "workspace_description")
    private String workspaceDescription;

    @Column(name = "workspace_image_url")
    private String workspaceImageUrl;

    @Column(name = "create_at")
    private LocalDateTime createAt;


    //------------------------------------------------

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private List<Member> member;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private List<PostCategory> postCategories;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL)
    private List<Directory> directories;

    @Builder
    public Workspace(String workspaceName, String workspaceDescription, String workspaceImageUrl, LocalDateTime createAt) {
        this.workspaceName = workspaceName;
        this.workspaceDescription = workspaceDescription;
        this.workspaceImageUrl = workspaceImageUrl;
        this.createAt = createAt;

    }

    public void updateImageWorkspace(String workspaceName, String workspaceDescription, String workspaceImageUrl) {
        this.workspaceName = workspaceName;
        this.workspaceDescription = workspaceDescription;
        this.workspaceImageUrl = workspaceImageUrl;
    }
    public void updateWorkspace(String workspaceName, String workspaceDescription) {
        this.workspaceName = workspaceName;
        this.workspaceDescription = workspaceDescription;
    }
}
