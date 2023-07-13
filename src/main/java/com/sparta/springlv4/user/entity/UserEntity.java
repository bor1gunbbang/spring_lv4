package com.sparta.springlv4.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "user_id" ,nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public UserEntity(String username, String password) {
        this.username= username;
        this.password = password;
    }
}
