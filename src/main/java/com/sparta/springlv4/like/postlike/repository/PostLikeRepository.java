package com.sparta.springlv4.like.postlike.repository;

import com.sparta.springlv4.like.postlike.entity.PostLikeEntity;
import com.sparta.springlv4.post.entity.PostEntity;
import com.sparta.springlv4.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasPasswordCallbackHandler;

import java.util.Locale;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLikeEntity, Locale> {
    Optional<PostLikeEntity> findByUserEntityAndPostEntity(UserEntity userEntity, PostEntity postEntity);
}
