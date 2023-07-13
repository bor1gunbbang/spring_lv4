package com.sparta.springlv4.common.security;

import jakarta.persistence.Column;
import org.hibernate.result.UpdateCountOutput;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user){this.user = user;}
    public User getUser(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return null;
    }

    public String getUsername(){
        return user.getUsername();
    }
}
