package com.music.streaming.controller;

import com.music.streaming.model.Artist;
import com.music.streaming.repository.ArtistRepository;
import com.music.streaming.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/artist/{id}")
@RequiredArgsConstructor
public class ArtistController {
    private final MusicService musicService;
    private final ArtistRepository artistRepository;

    @GetMapping
    public String getArtist(Model model, @PathVariable("id") Artist artist) {
        model.addAttribute("artist", artist);
        return "artist";
    }

    @GetMapping("/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editArtist(Model model, @PathVariable("id") Artist artist) {
        model.addAttribute("artist", artist);
        return "artist-edit";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteArtist(Model model, @PathVariable("id") Artist artist) {
        artistRepository.delete(artist);
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("filter", "");
        return "artists-list";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveEditArtist(
            Model model,
            @PathVariable("id") Artist artist,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String year,
            @RequestParam(required = false) MultipartFile file
    ) {
        if (!musicService.updateArtist(artist, name, description, year, file)) {
            model.addAttribute("message", "Unable to save changes!");
        }
        model.addAttribute("artist", artist);
        return "artist-edit";
    }
}
