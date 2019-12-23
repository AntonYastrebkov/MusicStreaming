package com.music.streaming.service;

import com.music.streaming.model.Album;
import com.music.streaming.model.Artist;
import com.music.streaming.model.Genre;
import com.music.streaming.model.Song;
import org.springframework.web.multipart.MultipartFile;

public interface MusicService {
    boolean addArtist(String name, String description, String year, MultipartFile file);

    boolean addAlbum(String name, String artistName, MultipartFile cover, Genre genre);

    boolean updateAlbum(Album album, String name, String artistName, MultipartFile cover, Genre genre);

    boolean updateArtist(Artist artist, String name, String description, String year, MultipartFile file);

    boolean addSong(Album album, String song);

    void deleteSong(Album album, Song song);
}
