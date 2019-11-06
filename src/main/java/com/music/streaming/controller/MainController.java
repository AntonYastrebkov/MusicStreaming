package com.music.streaming.controller;

import com.music.streaming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
        System.out.println("Controller");
    }

    @GetMapping("/main")
    public String main(Map <String, Object> model) {
        userService.method();
        return "main";
    }

    @GetMapping("/pain")
    public String painMethod(Map <String, Object> model) {
        return "pain";
    }
}
