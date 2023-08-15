package com.saikumar.MusicStreamingAPI.repository;

import com.saikumar.MusicStreamingAPI.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepo  extends JpaRepository<Song,Long> {

    Song findFirstBySongTitle(String songTitle);

}
