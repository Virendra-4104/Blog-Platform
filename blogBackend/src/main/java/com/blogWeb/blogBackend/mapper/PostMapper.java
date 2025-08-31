package com.blogWeb.blogBackend.mapper;

import com.blogWeb.blogBackend.dto.PostDTO;
import com.blogWeb.blogBackend.entity.Post;

public class PostMapper {
    public static PostDTO toDTO(Post post){
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthorId(post.getAuthorId());
        dto.setPostStatus(post.getPostStatus().name());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        dto.setLikes(post.getLikes());
        return dto;
    }
}
