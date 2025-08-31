package com.blogWeb.blogBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDTO {
    private String text;
    private String userId;
    private String postId;
}
