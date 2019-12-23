package com.music.streaming.service.implementation;

import com.music.streaming.model.Album;
import com.music.streaming.model.Artist;
import com.music.streaming.repository.AlbumRepository;
import com.music.streaming.repository.ArtistRepository;
import com.music.streaming.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    @Override
    public Page<Album> getAll(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    @Override
    public Page<Album> getByQuery(String request, Pageable pageable) {
        return albumRepository.findByNameLike(request, pageable);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public List<Artist> getArtistByQuery(String request) {
        return artistRepository.findByNameLike(request);
    }
}
