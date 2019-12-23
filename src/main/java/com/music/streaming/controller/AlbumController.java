package com.music.streaming.controller;

import com.music.streaming.model.Album;
import com.music.streaming.model.Comment;
import com.music.streaming.model.User;
import com.music.streaming.repository.CommentRepository;
import com.music.streaming.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/album/{id}")
@RequiredArgsConstructor
public class AlbumController {
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @GetMapping
    @Transactional
    public String getAlbum(Model model, @PathVariable("id") Album album) {
        model.addAttribute("album", album);
        model.addAttribute("comments", commentRepository.findAllByAlbumId(album.getId()));
        return "album";
    }

    @PostMapping("/addComment")
    public String addComment(
            Model model,
            @AuthenticationPrincipal User user,
            @PathVariable("id") Album album,
            String text,
            String mark
    ) {
        if (!commentService.addComment(album, user, text, mark)) {
            model.addAttribute("error", "Unable to add comment... Try again.");
        }
        model.addAttribute("album", album);
        model.addAttribute("comments", commentRepository.findAllByAlbumId(album.getId()));
        return "album";
    }

    @GetMapping("/comment/{commentId}/delete")
    public String deleteComment(Model model, @PathVariable("id") Album album, @PathVariable Long commentId) {
        commentService.deleteComment(album, commentId);
        model.addAttribute("album", album);
        model.addAttribute("comments", commentRepository.findAllByAlbumId(album.getId()));
        return "album";
    }

    @GetMapping("/comment/{commentId}/edit")
    public String editComment(Model model, @PathVariable("commentId") Comment comment) {
        model.addAttribute("comment", comment);
        return "comment-edit";
    }

    @PostMapping("/comment/{commentId}/edit")
    public String saveEditComment(
            Model model,
            @PathVariable("id") Album album,
            @PathVariable("commentId") Comment comment,
            String text,
            String mark
    ) {
        if (!commentService.editComment(album, comment, text, mark)) {
            model.addAttribute("error", "Unable to add comment... Try again.");
        }
        model.addAttribute("album", album);
        model.addAttribute("comments", commentRepository.findAllByAlbumId(album.getId()));
        return "album";
    }
}
