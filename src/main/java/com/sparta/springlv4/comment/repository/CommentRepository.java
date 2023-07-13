package com.sparta.springlv4.comment.repository;

import com.sparta.springlv4.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByPostIdOrderByCreatedAtDesc(Long postId);
    List<CommentEntity> findAllByPost_Id(Long postId);

}
