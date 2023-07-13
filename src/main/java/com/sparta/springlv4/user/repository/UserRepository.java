package com.sparta.springlv4.user.repository;

import com.sparta.springlv4.user.entity.User;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {



}
