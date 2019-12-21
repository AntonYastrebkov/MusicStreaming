package com.music.streaming.service;

import com.music.streaming.model.Album;
import com.music.streaming.model.Artist;
import com.music.streaming.model.Genre;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MusicService {
    boolean addArtist(Artist artist);

    boolean addAlbum(String name, String artistName, MultipartFile cover, Genre genre);

    boolean updateAlbum(Album album, String name, String artistName, MultipartFile cover, Genre genre);
}