package com.music.streaming.controller;

import com.music.streaming.model.Role;
import com.music.streaming.model.User;
import com.music.streaming.repository.UserRepository;
import com.music.streaming.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }


}
