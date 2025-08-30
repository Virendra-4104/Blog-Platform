package com.blogWeb.blogBackend.repository;

import com.blogWeb.blogBackend.entity.Post;
import com.blogWeb.blogBackend.entity.PostStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
    List<Post> findByAuthorId(String authorId);
    List<Post> findByPostStatus(PostStatus postStatus);
}
