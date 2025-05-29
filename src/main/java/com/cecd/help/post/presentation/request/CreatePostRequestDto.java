package com.cecd.help.post.presentation.request;

public record CreatePostRequestDto(
        String postTitle,
        String postDescription,
        String postCategory
) {
}
