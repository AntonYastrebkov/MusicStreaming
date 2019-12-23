package com.music.streaming.controller;

import com.music.streaming.model.Album;
import com.music.streaming.model.Genre;
import com.music.streaming.model.Song;
import com.music.streaming.repository.AlbumRepository;
import com.music.streaming.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

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
            model.addAttribute("message", "Artist already exists!");
        } else {
            model.addAttribute("message", "Artist added");
        }
        model.addAttribute("genres", Genre.values());
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
        System.out.println(name + " " + artistName);
        if (!musicService.addAlbum(name, artistName, cover, genre)) {
            model.addAttribute("message", "Impossible to add album!");
        } else {
            model.addAttribute("message", "Album added");
        }
        model.addAttribute("genres", Genre.values());
        return "music-manage";
    }

    @GetMapping("/album/{id}")
    @Transactional
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
    @Transactional
    public String albumEdit(
            Model model,
            @RequestParam String name,
            @RequestParam String artistName,
            @RequestParam Genre genre,
            @RequestParam(value = "file", required = false) MultipartFile cover,
            @PathVariable("id") Album album
    ) {
        if (!musicService.updateAlbum(album, name, artistName, cover, genre)) {
            model.addAttribute("message", "Something went wrong");
        }
        model.addAttribute("genres", Genre.values());
        model.addAttribute("album", album);
        return "album-edit";
    }

    @PostMapping("/album/{id}/addSong")
    public String addSong(
            Model model,
            @PathVariable("id") Album album,
            @RequestParam String name
    ) {
        if (!musicService.addSong(album, name)) {
            model.addAttribute("message", "Unable to add a song!");
        }
        model.addAttribute("genres", Genre.values());
        model.addAttribute("album", album);
        return "album-edit";
    }

    @GetMapping("/album/{id}/song/{songId}/delete")
    @Transactional
    public String deleteSong(
            Model model,
            @PathVariable("id") Album album,
            @PathVariable("songId") Song song
    ) {
        musicService.deleteSong(album, song);
        model.addAttribute("genres", Genre.values());
        model.addAttribute("album", album);
        return "album-edit";
    }
}
