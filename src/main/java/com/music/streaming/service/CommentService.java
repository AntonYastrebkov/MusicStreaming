package com.music.streaming.service;

import com.music.streaming.model.Album;
import com.music.streaming.model.Comment;
import com.music.streaming.model.User;

public interface CommentService {
    boolean addComment(Album album, User user, String text, String mark);

    void deleteComment(Album album, Long commentId);

    boolean editComment(Album album, Comment comment, String text, String mark);
}
