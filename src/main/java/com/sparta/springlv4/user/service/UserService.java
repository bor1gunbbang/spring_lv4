package com.sparta.springlv4.user.service;

import com.sparta.springlv4.common.jwt.JwtUtil;
import com.sparta.springlv4.user.dto.UserRequestDto;
import com.sparta.springlv4.user.entity.UserEntity;
import com.sparta.springlv4.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public void signup(UserRequestDto requestDto) throws Exception {
        String username = requestDto.getUsername();
        String passwrod = passwordEncoder.encode(requestDto.getPassword());

        Optional<UserEntity> checkUsername = userRepository.findById(username);
        if (checkUsername.isPresent())  {
            throw new IllegalArgumentException("존재하는 username입니다.");
        }
        UserEntity user = new UserEntity(username,passwrod);
        userRepository.save(user);
    }

    public void login(UserRequestDto requestDto, HttpServletResponse res) {

        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        UserEntity user = userRepository.findById(username).orElseThrow(
                ()->new IllegalArgumentException("유저를 찾을 수 없습니다.")
        );

        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new IllegalArgumentException("암호가 틀립니다.");
        }
        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token,res);
    }
}
