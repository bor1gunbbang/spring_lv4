package com.sparta.springlv4.user.service;

import com.sparta.springlv4.user.dto.UserRequestDto;
import com.sparta.springlv4.user.entity.User;
import com.sparta.springlv4.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signup(UserRequestDto requestDto) throws Exception {
        String username = requestDto.getUsername();
        String passwrod = passwordEncoder.encode(requestDto.getPassword());

        Optional<User> checkUsername = userRepository.findById(username);
        if (checkUsername.isPresent())  {
            throw new IllegalArgumentException("존재하는 username입니다.");
        }
        User user = new User(username,passwrod);
        userRepository.save(user);
    }
}
