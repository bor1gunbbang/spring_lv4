package com.sparta.springlv4.like.commentlike.entity;


import com.sparta.springlv4.comment.entity.CommentEntity;
import com.sparta.springlv4.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment_like_table")
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CommentEntity commentEntity;

    public CommentLikeEntity(UserEntity userEntity, CommentEntity commentEntity) {
        this.userEntity = userEntity;
        this.commentEntity = commentEntity;
    }
}
