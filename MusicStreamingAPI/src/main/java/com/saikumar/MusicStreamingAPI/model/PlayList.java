package com.saikumar.MusicStreamingAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playListId;
    private String playListName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime playListCreationTimeStamp;
    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    User PlayListOwner;
    @ManyToMany
    @JoinTable(name = "playlist_song_jt",joinColumns = @JoinColumn(name = "fk_playlist_id"),inverseJoinColumns = @JoinColumn(name = "fk_song_id"))
    List<Song> songs;

}
