package com.cecd.help.post.domain.entity;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "post_categories")
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "postCategory", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "workspace_id")
    private Workspace workspace;

    @Builder
    public PostCategory(String categoryName, Workspace workspace) {
        this.categoryName = categoryName;
        this.workspace = workspace;
    }


}
