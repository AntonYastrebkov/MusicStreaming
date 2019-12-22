package com.music.streaming.controller;

import com.music.streaming.model.Album;
import com.music.streaming.model.Artist;
import com.music.streaming.repository.ArtistRepository;
import com.music.streaming.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainPageService mainPageService;
    private final ArtistRepository artistRepository;

    @GetMapping("/")
    public String greetings(Model model) {
        return "greetings";
    }

    @GetMapping("/main")
    public String main(
            Model model,
            String filter,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 9) Pageable pageable
    ) {
        Page<Album> albums;
        if (StringUtils.isEmpty(filter)) {
            albums = mainPageService.getAll(pageable);
        } else {
            albums = mainPageService.getByQuery(filter, pageable);
        }
        model.addAttribute("url", "/main");
        model.addAttribute("albums", albums);
        model.addAttribute("filter", filter);
        return "main";
    }

    @GetMapping("/artists")
    public String artists(Model model, String filter) {
        Iterable<Artist> artists;
        if (StringUtils.isEmpty(filter)) {
            artists = mainPageService.getAllArtists();
        } else {
            artists = mainPageService.getArtistByQuery(filter);
        }
        model.addAttribute("artists", artists);
        model.addAttribute("filter", filter);
        return "artists-list";
    }
}
