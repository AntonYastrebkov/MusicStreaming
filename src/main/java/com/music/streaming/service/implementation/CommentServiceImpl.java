package com.music.streaming.service.implementation;

import com.music.streaming.model.Album;
import com.music.streaming.model.Comment;
import com.music.streaming.model.User;
import com.music.streaming.repository.CommentRepository;
import com.music.streaming.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public boolean addComment(Album album, User user, String text, String mark) {
        Comment comment = new Comment();
        comment.setAlbum(album);
        comment.setUser(user);
        comment.setText(text);
        try {
            comment.setMark(Integer.parseInt(mark));
        } catch (NumberFormatException e) {
            return false;
        }
        comment.setTime(LocalDateTime.now());
        commentRepository.save(comment);
        evaluateAverage(album);
        return true;
    }

    @Override
    public void deleteComment(Album album, Long commentId) {
        commentRepository.deleteById(commentId);
        evaluateAverage(album);
    }

    @Override
    public boolean editComment(Album album, Comment comment, String text, String mark) {
        comment.setText(text);
        try {
            comment.setMark(Integer.parseInt(mark));
        } catch (NumberFormatException e) {
            return false;
        }
        commentRepository.save(comment);
        evaluateAverage(album);
        return true;
    }

    private void evaluateAverage(Album album) {
        List<Comment> allByAlbumId = commentRepository.findAllByAlbumId(album.getId());
        double average = allByAlbumId.stream()
                .mapToInt(Comment::getMark)
                .average().orElseGet(() -> 0.0);
        album.setAverageScore(average);
    }
}
