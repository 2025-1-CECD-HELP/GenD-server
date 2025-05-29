package com.cecd.help.document.domain.entity;

import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "directory")
public class Directory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "dir_name")
    private String dirName;

    @Column(nullable = false, name = "par_id")
    private Long parId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "workspace_id")
    private Workspace workspace;


    @OneToMany(mappedBy = "directory", cascade = CascadeType.ALL)
    private List<Store> stores;

    @Builder
    public Directory(String dirName, Long parId, Workspace workspace) {
        this.dirName = dirName;
        this.parId = parId;
        this.workspace = workspace;
    }

    public void updateDirName(String dirName) {
        this.dirName = dirName;
    }
}
