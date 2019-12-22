package com.music.streaming.controller;

import com.music.streaming.model.Album;
import com.music.streaming.model.Artist;
import com.music.streaming.model.Genre;
import com.music.streaming.repository.AlbumRepository;
import com.music.streaming.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/music-manage")
@RequiredArgsConstructor
public class MusicManagmentController {
    private final MusicService musicService;
    private final AlbumRepository albumRepository;

    @GetMapping
    public String getManagePage(Model model) {
        model.addAttribute("genres", Genre.values());
        return "music-manage";
    }

    @PostMapping("/addArtist")
    public String addArtist(
            Model model,
            String name,
            String description,
            String year,
            @RequestParam(name = "image", required = false) MultipartFile image
    ) {
        if (!musicService.addArtist(name, description, year, image)) {
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

    @GetMapping("/album/{id}")
    public String getAlbumEdit(Model model, @PathVariable("id") Album album) {
        model.addAttribute("genres", Genre.values());
        model.addAttribute("album", album);
        return "album-edit";
    }

    @GetMapping("/album/{id}/delete")
    public String deleteAlbum(Model model, @PathVariable("id") Album album) {
        albumRepository.delete(album);
        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("filter", "");
        return "main";
    }

    @PostMapping("/album/{id}")
    public String albumEdit(
            Model model,
            @RequestParam String name,
            @RequestParam String artistName,
            @RequestParam Genre genre,
            @RequestParam(value = "file", required = false) MultipartFile cover,
            @PathVariable("id") Album album
    ) {
        if (!musicService.updateAlbum(album, name, artistName, cover, genre)) {
            model.addAttribute("genres", Genre.values());
            model.addAttribute("album", album);
            model.addAttribute("message", "Something went wrong");
            return "album-edit";
        }
        model.addAttribute("genres", Genre.values());
        model.addAttribute("album", album);
        return "album-edit";
    }
}
