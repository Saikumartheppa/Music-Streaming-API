package com.saikumar.MusicStreamingAPI.repository;

import com.saikumar.MusicStreamingAPI.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayListRepo  extends JpaRepository<PlayList,Long> {

    PlayList findFirstByPlayListName(String playListName);

}
