package com.music.streaming.controller;

import com.music.streaming.model.Artist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artist/{id}")
@RequiredArgsConstructor
public class ArtistController {
    @GetMapping
    public String getArtist(Model model, @PathVariable("id") Artist artist) {
        model.addAttribute("artist", artist);
        return "artist";
    }
}
