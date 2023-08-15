package com.saikumar.MusicStreamingAPI.service;

import com.saikumar.MusicStreamingAPI.model.Song;
import com.saikumar.MusicStreamingAPI.repository.ISongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    @Autowired
    ISongRepo songRepo;

    public Song findFirstBySongTitle(String songTitle) {
     return  songRepo.findFirstBySongTitle(songTitle);
    }

    public String addSong(Song song) {
        songRepo.save(song);
        return "Song"+" : " + song.getSongTitle() +" added successfully";
    }

    public Song getSongById(Long sId) {
       return songRepo.findById(sId).orElse(null);
    }

    public Iterable<Song> getAllSongs() {
        return songRepo.findAll();
    }

    public boolean isValidTitle(String title) {
        Song song = findFirstBySongTitle(title);
        return song == null;
    }

    public String UpdateSongById(Long songId, String title) {
        Song song = getSongById(songId);
        if(song != null) {
            if (isValidTitle(title)) {
                song.setSongTitle(title);
                songRepo.save(song);
                return "Song : "+songId+ ", newTitle : "+ title +"... updated successfully!!";
            }else{
                return "Song title Already exist!!! try again";
            }
        }else{
            return "No such song : " + songId+" exist!!";
        }
    }
    public void removeSong(Song song) {
        songRepo.delete(song);
    }

}
