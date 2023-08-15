package com.saikumar.MusicStreamingAPI.service;

import com.saikumar.MusicStreamingAPI.model.PlayList;
import com.saikumar.MusicStreamingAPI.model.Song;
import com.saikumar.MusicStreamingAPI.repository.IPlayListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayListService {
    @Autowired
    IPlayListRepo playListRepo;
    @Autowired
    SongService songService;

    public String addPlayList(PlayList playList) {
       playList.setPlayListCreationTimeStamp(LocalDateTime.now());
       playListRepo.save(playList);
       return "PlayList : "+playList.getPlayListName()+" created successfully!! Add your favourite songs to playList!!";
    }

    public PlayList findPlayList(String playListName) {
        return playListRepo.findFirstByPlayListName(playListName);
    }

    public List<PlayList> getAllPlayLists() {
        return playListRepo.findAll();
    }

    public String addSongsToPlayList(PlayList playList,List<Song> existingSongs,LocalDateTime creationTime) {

        List<Song> newSongs = playList.getSongs();
        List<Song> toBeAdded = new ArrayList<>();
//            existingSongs.addAll(newSongs);
//            playList.setSongs(existingSongs);
//            playList.setPlayListCreationTimeStamp(creationTime);
//            playListRepo.save(playList);
              for(int i = 0; i < newSongs.size();i++){
                  Song song = newSongs.get(i);
                  if(songService.getSongById(song.getSongId()) != null){
                      if(!existingSongs.contains(song)){
                          toBeAdded.add(song);
                      }else{
                          System.out.println("Song Already exist!!!");
                      }
                  }else{
                      System.out.println("No such song exist!!!!");
                  }
              }
          //  System.out.println(existingSongs);
            existingSongs.addAll(toBeAdded);
            playList.setSongs(existingSongs);
            playList.setPlayListCreationTimeStamp(creationTime);
            playListRepo.save(playList);
            return "Songs added to PlayList : " + playList.getPlayListName();
    }



    public PlayList findPlayListById(Long passedId) {
        return playListRepo.findById(passedId).orElse(null);
    }

    public String updatePlayList(PlayList playList,List<Song> existingSongs) {
       playList.setPlayListName(playList.getPlayListName());
       playList.setPlayListCreationTimeStamp(LocalDateTime.now());
       playList.setSongs(existingSongs);
       playListRepo.save(playList);
       return "playList  updated";
    }

    public String remove(PlayList playList) {
        playListRepo.delete(playList);
        return "PlayList deleted successfully";
    }

    public List<Song> getAllSongsByPlayListId(PlayList playList) {
        List<Song> songs = playList.getSongs();
        return songs;
    }

    public Song findSongById(PlayList playList, Long songId) {
        List<Song> songs = getAllSongsByPlayListId(playList);
        for(Song mySong : songs){
            if(mySong.getSongId() == songId){
                return mySong;
            }
        }
        return null;
    }

    public String removeSong(PlayList playList,Song song) {
        List<Song> songs = getAllSongsByPlayListId(playList);
        for(int i = 0; i < songs.size(); i++){
            if(songs.get(i).getSongId() == song.getSongId()){
                songs.remove(i);
            }
        }
        playList.setSongs(songs);
        playListRepo.save(playList);
        return "Song removed Successfully";
    }
}
