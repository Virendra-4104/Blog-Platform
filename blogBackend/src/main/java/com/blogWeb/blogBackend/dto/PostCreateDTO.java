package com.blogWeb.blogBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDTO {
    private String title;
    private String content;
    private String authorId;
}
