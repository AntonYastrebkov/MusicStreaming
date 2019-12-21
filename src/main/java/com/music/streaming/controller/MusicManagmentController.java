package com.music.streaming.controller;

import com.music.streaming.model.Artist;
import com.music.streaming.model.Genre;
import com.music.streaming.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/music-manage")
@RequiredArgsConstructor
public class MusicManagmentController {
    private final MusicService musicService;

    @GetMapping
    public String getManagePage(Model model) {
        model.addAttribute("genres", Genre.values());
        return "music-manage";
    }

    @PostMapping("/addArtist")
    public String addArtist(
            Model model,
            Artist artist
    ) {
        if (!musicService.addArtist(artist)) {
            model.addAttribute("genres", Genre.values());
            model.addAttribute("message", "Artist already exists!");
            return "music-manage";
        }
        model.addAttribute("genres", Genre.values());
        model.addAttribute("message", "Artist added");
        return "music-manage";
    }

    @PostMapping("/addAlbum")
    public String addAlbum(
            Model model,
            @RequestParam String name,
            @RequestParam String artistName,
            @RequestParam Genre genre,
            @RequestParam("file") MultipartFile cover
    ) {
        System.out.println(genre.name());
        if (!musicService.addAlbum(name, artistName, cover, genre)) {
            model.addAttribute("genres", Genre.values());
            model.addAttribute("message", "Impossible to add album!");
            return "music-manage";
        }
        model.addAttribute("message", "Album added");
        model.addAttribute("genres", Genre.values());
        return "music-manage";
    }
}
