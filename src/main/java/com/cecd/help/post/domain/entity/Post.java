package com.cecd.help.post.domain.entity;

import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "post_title")
    private String postTitle;

    @Column(nullable = false, name = "post_description")
    private String postDescription;

    @Column(nullable = false, name = "post_imageUrl")
    private String postImageUrl;

    @Column(nullable = false, name = "is_pin")
    private Boolean isPin;

    @Column(nullable = false, name = "create_at")
    private LocalDate createAt;


    //--------------------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "workspace_id")
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "category_id")
    private PostCategory postCategory;

    @Builder
    public Post(String postTitle, String postDescription, String postImageUrl, Member member, Workspace workspace, PostCategory postCategory) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postImageUrl = postImageUrl;
        this.postCategory = postCategory;
        this.member = member;
        this.workspace = workspace;
        this.isPin = false;
        this.createAt = LocalDate.now();
    }

    public void updatePostImage(String postTitle, String postDescription, PostCategory category, String postImageUrl) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postCategory = category;
        this.postImageUrl = postImageUrl;
    }

    public void updatePost(String postTitle, String postDescription, PostCategory category) {
        this.postTitle = postTitle;
        this.postDescription = postDescription;
        this.postCategory = category;
    }

    public void updatePin(Boolean pin) {
        this.isPin = !pin;
    }

}
