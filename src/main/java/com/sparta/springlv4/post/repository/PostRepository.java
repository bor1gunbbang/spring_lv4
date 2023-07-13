package com.sparta.springlv4.post.repository;

import com.sparta.springlv4.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

public interface PostRepository extends JpaRepository<Post,Long> {
}
