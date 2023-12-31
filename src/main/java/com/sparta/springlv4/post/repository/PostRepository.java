package com.sparta.springlv4.post.repository;

import com.sparta.springlv4.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findAllByOrderByCreatedAtDesc();
}
