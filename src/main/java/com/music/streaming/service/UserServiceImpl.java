package com.music.streaming.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void method() {
        System.out.println("Service!");
    }
}
