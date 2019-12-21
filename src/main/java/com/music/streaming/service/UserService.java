package com.music.streaming.service;

import com.music.streaming.model.User;
import com.music.streaming.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean registerUser(UserDto userDto);

    boolean activateUser(String code);

    User getUser(Long id);
}
