package com.sparta.springlv4.like.commentlike.repository;

import com.sparta.springlv4.comment.entity.CommentEntity;
import com.sparta.springlv4.like.commentlike.entity.CommentLikeEntity;
import com.sparta.springlv4.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Long> {
    Optional<CommentLikeEntity> findByUserEntityAndCommentEntity(UserEntity user, CommentEntity comment);
}
