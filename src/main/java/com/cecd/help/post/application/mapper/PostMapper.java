package com.cecd.help.post.application.mapper;

import com.cecd.help.post.domain.entity.Post;
import com.cecd.help.post.domain.entity.PostCategory;
import com.cecd.help.post.presentation.request.CreatePostRequestDto;
import com.cecd.help.workspace.domain.entity.Member;
import com.cecd.help.workspace.domain.entity.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostMapper {

    public Post newPost(String postTitle, String postDescription, String postImageUrl, Member member, Workspace workspace, PostCategory postCategory) {
        return Post.builder()
                .postTitle(postTitle)
                .postDescription(postDescription)
                .postImageUrl(postImageUrl)
                .member(member)
                .postCategory(postCategory)
                .workspace(workspace)
                .build();
    }
}
