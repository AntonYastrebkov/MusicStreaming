package com.music.streaming.repository;

import com.music.streaming.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByAlbumId(Long albumId);
}
