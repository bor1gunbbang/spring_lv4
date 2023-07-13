package com.sparta.springlv4.user.repository;

import com.sparta.springlv4.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {



}
