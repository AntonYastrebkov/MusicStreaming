package com.music.streaming.service;

import com.music.streaming.model.User;
import com.music.streaming.model.UserDto;

public interface UserService {
    void method();

    User getUser(Long id);

    User registerNewUser(UserDto userDto);
}
