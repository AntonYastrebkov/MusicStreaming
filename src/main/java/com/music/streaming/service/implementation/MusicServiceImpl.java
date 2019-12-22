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
public class MusicServiceImpl implements MusicService {
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public boolean addArtist(String name, String description, String year, MultipartFile file) {
        Artist artistFromDb = artistRepository.findByName(name);
        if (artistFromDb != null) {
            return false;
        }
        Artist artist = new Artist();
        artist.setName(name);
        artist.setDescription(description);
        artist.setYear(year);
        if (file != null && !saveArtistImage(artist, file)) {
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
        if (! saveCover(album, cover)) {
            return false;
        }

        albumRepository.save(album);

        return true;
    }

    @Override
    public boolean updateAlbum(Album album, String name, String artistName, MultipartFile cover, Genre genre) {
        album.setName(name);
        Artist artist = artistRepository.findByName(artistName);
        if (artist == null) {
            return false;
        }
        album.setArtist(artist);
        album.setGenre(genre);
        if (cover != null && !saveCover(album, cover)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateArtist(Artist artist, String name, String description, String year, MultipartFile file) {
        artist.setName(name);
        artist.setDescription(description);
        artist.setYear(year);
        if (file != null) {
            return saveArtistImage(artist, file);
        }
        return true;
    }

    private boolean saveArtistImage(Artist artist, MultipartFile image) {
        String filename = saveImage(image);
        System.out.println(filename);
        if ("".equals(filename)) {
            return false;
        }
        artist.setImagePath(filename);
        return true;
    }

    private boolean saveCover(Album album, MultipartFile cover) {
        String filename = saveImage(cover);
        if ("".equals(filename)) {
            return false;
        }
        album.setCoverPath(filename);
        return true;
    }

    private String saveImage(MultipartFile file) {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File destination = new File(uploadPath);
            if (!destination.exists()) {
                destination.mkdir();
            }

            String filename = UUID.randomUUID().toString() + "."
                    + file.getOriginalFilename();
            try {
                file.transferTo(new File(uploadPath + "/" + filename));
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }

            return filename;
        } else {
            return "";
        }
    }

}
