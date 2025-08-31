package com.blogWeb.blogBackend.mapper;

import com.blogWeb.blogBackend.dto.CommentDTO;
import com.blogWeb.blogBackend.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentMapper {
    public  static CommentDTO toDTO(Comment comment){
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setUserId(comment.getUserId());
        dto.setPostId(comment.getPostId());
        dto.setCreatedAt(comment.getCreatedAt());
        return dto;
    }
}
