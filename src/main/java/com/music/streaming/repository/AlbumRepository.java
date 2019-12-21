package com.music.streaming.repository;

import com.music.streaming.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByName(String name);

    List<Album> findByArtistId(Long artistId);
}
