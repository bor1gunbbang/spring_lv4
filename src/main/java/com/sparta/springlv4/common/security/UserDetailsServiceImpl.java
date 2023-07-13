package com.sparta.springlv4.common.security;

import com.sparta.springlv4.user.entity.UserEntity;
import com.sparta.springlv4.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl {
    private final UserRepository userRepository;
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()->
                        new UsernameNotFoundException("찾지못했습니다. " + id));

        return new UserDetailsImpl(user);
    }
}
