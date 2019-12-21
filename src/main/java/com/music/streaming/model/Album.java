package com.music.streaming.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String coverPath;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Comment> comments;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "song_id")
    private Set<Song> songs;

}
