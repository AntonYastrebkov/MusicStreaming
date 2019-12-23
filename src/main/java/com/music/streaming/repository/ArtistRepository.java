package com.music.streaming.repository;

import com.music.streaming.model.Album;
import com.music.streaming.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findByName(String name);

    @Query("SELECT a FROM Artist a WHERE a.name LIKE CONCAT('%',:request,'%')")
    List<Artist> findByNameLike(@Param("request") String request);
}
