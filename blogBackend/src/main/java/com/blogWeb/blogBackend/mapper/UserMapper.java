package com.blogWeb.blogBackend.mapper;

import com.blogWeb.blogBackend.dto.UserDTO;
import com.blogWeb.blogBackend.entity.User;
import lombok.Data;

@Data
public class UserMapper {
    public static UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
