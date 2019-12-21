package com.music.streaming.service.implementation;

import com.music.streaming.model.Role;
import com.music.streaming.model.User;
import com.music.streaming.model.UserDto;
import com.music.streaming.repository.UserRepository;
import com.music.streaming.service.MailService;
import com.music.streaming.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MailService mailService;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        User userFromDb = userRepository.findByEmail(userDto.getEmail());
        if (userFromDb != null) {
            return false;
        }
//        userFromDb = new User();
//        userFromDb.setEmail(userDto.getEmail());
//        userFromDb.setUsername(userDto.getUsername());
//        userFromDb.setPassword(userDto.getPassword());
//        userFromDb.setFirstName(userDto.getFirstName());
//        userFromDb.setLastName(userDto.getLastName());
//        userFromDb.setRoles(Collections.singleton(Role.USER));
//        userFromDb.setActivationCode(UUID.randomUUID().toString());
        userFromDb = new User();
        userFromDb.setEmail("anton.yastrebkoff@gmail.com");
        userFromDb.setUsername("admin");
        userFromDb.setPassword("admin");
        userFromDb.setFirstName("Unknown");
        userFromDb.setLastName("Capybara");
        userFromDb.setRoles(Set.of(Role.USER, Role.ADMIN));
        userFromDb.setActive(true);
        userRepository.save(userFromDb);

        if (!StringUtils.isEmpty(userFromDb.getEmail())) {
            mailService.send(
                    userFromDb.getEmail(),
                    "Activation code",
                    String.format(
                            "Hello, %s!\n" +
                                    "Welcome to awful Sweater! We don't know why you need it, " +
                                    "but to confirm registration follow the link:\n" +
                                    "http://localhost:8080/activate/%s",
                            userFromDb.getFirstName() + userFromDb.getLastName(),
                            userFromDb.getActivationCode()
                    ));
        }
        return true;
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        return userRepository.findByUsername(username);
    }
}
