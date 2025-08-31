package com.blogWeb.blogBackend.service;

import com.blogWeb.blogBackend.dto.LoginDTO;
import com.blogWeb.blogBackend.dto.UpdateUserDTO;
import com.blogWeb.blogBackend.dto.UserDTO;
import com.blogWeb.blogBackend.dto.UserRegisterDTO;
import com.blogWeb.blogBackend.entity.Comment;
import com.blogWeb.blogBackend.entity.Post;
import com.blogWeb.blogBackend.entity.User;
import com.blogWeb.blogBackend.mapper.UserMapper;
import com.blogWeb.blogBackend.repository.CommentRepository;
import com.blogWeb.blogBackend.repository.PostRepository;
import com.blogWeb.blogBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private PostRepository postRepository;
    @Autowired private CommentRepository commentRepository;

//    username validation
    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Username cannot be empty");
        }

        if (username.contains(" ")) {
            throw new RuntimeException("Username cannot contain spaces");
        }

        if (username.length() < 3) {
            throw new RuntimeException("Username must be at least 3 characters long");
        }

        if (username.length() > 20) {
            throw new RuntimeException("Username cannot exceed 20 characters");
        }

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new RuntimeException("Username can only contain letters, numbers, and underscores");
        }
    }

    public UserDTO registerUser(UserRegisterDTO request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }
        if (userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Username already in exists");
        }
        validateUsername(request.getUsername());
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    public UserDTO login(LoginDTO loginDTO){
        Optional<User> userOpt = userRepository.findByUsername(loginDTO.getIdentifier());

        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByEmail(loginDTO.getIdentifier());
        }
        User user = userOpt.orElseThrow(()-> new RuntimeException("User not found"));
        if (!user.getPassword().equals(loginDTO.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        return UserMapper.toDTO(user);
    }

    public UserDTO updateUser(String userId, UpdateUserDTO dto){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));

        if (dto.getName() != null && !dto.getName().trim().isEmpty()){
            user.setName(dto.getName());
        }

        if (dto.getUsername() != null && !dto.getUsername().equals(user.getUsername())){
            if (userRepository.existsByUsername(dto.getUsername())) {
                throw new RuntimeException("Username already taken");
            }
            validateUsername(dto.getUsername());
            user.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            user.setPassword(dto.getPassword());
        }
        return UserMapper.toDTO(userRepository.save(user));
    }

    public void deleteUser(String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        //  Delete all posts by this user
        List<Post> userPosts = postRepository.findByAuthorId(userId);
        for (Post post : userPosts) {
            // delete post comments first
            if (post.getCommentsId() != null) {
                for (String commentId : post.getCommentsId()) {
                    commentRepository.deleteById(commentId);
                }
            }
            postRepository.delete(post);
        }

        //  Delete all comments made by this user
        List<Comment> userComments = commentRepository.findByUserId(userId);
        for (Comment comment : userComments) {
            commentRepository.delete(comment);
        }

        // Remove likes from other posts
        List<Post> likedPosts = postRepository.findAll();
        for (Post post : likedPosts) {
            if (post.getLikes().contains(userId)) {
                post.getLikes().remove(userId);
                postRepository.save(post);
            }
        }

        // Delete the user itself
        userRepository.delete(user);
    }
}
