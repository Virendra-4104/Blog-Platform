package com.blogWeb.blogBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    private String authorId;
    private PostStatus postStatus = PostStatus.DRAFT;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<String> likes = new ArrayList<>();
}
