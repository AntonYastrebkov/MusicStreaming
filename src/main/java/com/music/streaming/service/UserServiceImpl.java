package com.music.streaming.service;

import com.music.streaming.model.User;
import com.music.streaming.model.UserDto;
import com.music.streaming.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void method() {
        System.out.println("Service!");
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User registerNewUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new RuntimeException("Email is already in use");
        }
        user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getName());
        userRepository.save(user);

        return user;
    }
}
