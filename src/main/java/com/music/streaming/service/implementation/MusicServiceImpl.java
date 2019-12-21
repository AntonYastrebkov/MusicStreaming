package com.music.streaming.service.implementation;

import com.music.streaming.model.Album;
import com.music.streaming.model.Artist;
import com.music.streaming.model.Genre;
import com.music.streaming.repository.AlbumRepository;
import com.music.streaming.repository.ArtistRepository;
import com.music.streaming.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
// @RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public boolean addArtist(Artist artist) {
        Artist artistFromDb = artistRepository.findByName(artist.getName());
        if (artistFromDb != null) {
            return false;
        }
        artistRepository.save(artist);
        return true;
    }

    @Override
    public boolean addAlbum(String name, String artistName, MultipartFile cover, Genre genre) {
        List<Album> albumByName = albumRepository.findByName(name);
        Artist artist = artistRepository.findByName(artistName);

        System.out.println(artist.getName());

        Optional<Album> optional = albumByName.stream()
                .filter(a -> a.getArtist().getName().equals(artistName))
                .findFirst();
        if (artist == null || optional.isPresent()) {
            return false;
        }
        Album album = new Album();
        album.setName(name);
        album.setArtist(artist);
        album.setGenre(genre);

        if (cover != null && !cover.getOriginalFilename().isEmpty()) {
            File destination = new File(uploadPath);
            if (!destination.exists()) {
                destination.mkdir();
            }

            String filename = UUID.randomUUID().toString() + "."
                    + cover.getOriginalFilename();
            try {
                cover.transferTo(new File(uploadPath + "/" + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(filename);
            album.setCoverPath(filename);
        }
        albumRepository.save(album);

        return true;
    }
}
