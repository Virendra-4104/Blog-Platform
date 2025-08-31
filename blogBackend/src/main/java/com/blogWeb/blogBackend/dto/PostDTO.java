package com.blogWeb.blogBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String id;
    private String title;
    private String content;
    private String authorId;
    private String postStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> likes;
}
