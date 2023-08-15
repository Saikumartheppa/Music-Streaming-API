package com.saikumar.MusicStreamingAPI.controller;

import com.saikumar.MusicStreamingAPI.model.PlayList;
import com.saikumar.MusicStreamingAPI.model.Song;
import com.saikumar.MusicStreamingAPI.model.User;
import com.saikumar.MusicStreamingAPI.model.dtos.user.SignInInput;
import com.saikumar.MusicStreamingAPI.model.dtos.user.SignUpOutput;
import com.saikumar.MusicStreamingAPI.service.AuthenticationTokenService;
import com.saikumar.MusicStreamingAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    // signUp,signIn,singOut
    @PostMapping("userSignUp")
    public SignUpOutput userSignUp(@RequestBody  User user){
        return userService.userSignUp(user);
    }
    @PostMapping("userSignIn")
    public String userSignIn(@RequestBody  SignInInput signInInput){
        return userService.userSignIn(signInInput);
    }
    @DeleteMapping("userSignOut")
    public String userSignOut(@RequestParam String email, @RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
            return userService.userSignOut(email);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    // CRUD functionalities in PlayList

    // creating a playList
    @PostMapping("playList")
    public String addPlayList(@RequestBody PlayList playList, @RequestParam String email, @RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
            return userService.addPlayList(playList,email);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    // fetching all playLists
    @GetMapping("playLists")
    public List<PlayList> getAllPlayLists(@RequestParam String email, @RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
          return  userService.getAllPlayLists();
        }else{
            throw new RuntimeException("UnAuthorised user Activity");
        }
    }
    // fetch all Songs in a playList
    @GetMapping("songs/playList/{playListId}")
    public List<Song> getAllSongsByPlayListId(@PathVariable Long playListId, @RequestParam String email, @RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
           return userService.getAllSongsByPlayListId(playListId,email);
        }else{
            throw new RuntimeException("UnAuthorised user Activity");
        }
    }
    // add songs to playList
    @PostMapping("playList/songs")
    public String addSongsToPlayList(@RequestBody PlayList playList,@RequestParam String email, @RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
          return  userService.addSongsToPlayList(playList,email);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    // update playList
    @PutMapping("playList")
    public String updatePlayList(@RequestBody PlayList playList,@RequestParam String email, @RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
            return  userService.updatePlayList(playList,email);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    // delete playList by Id
    @DeleteMapping("playList/{playListId}")
    public String deletePlayListById(@PathVariable Long playListId,@RequestParam String email,@RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
            return userService.deletePlayListById(playListId,email);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    // delete song in a playList
    @DeleteMapping("playList/{playListId}/songs/song/{songId}")
    public String deleteSongById(@PathVariable Long playListId,@PathVariable Long songId,@RequestParam String email,@RequestParam String tokenValue){
        if(authenticationTokenService.authenticateUser(email,tokenValue)){
            return userService.deleteSongId(playListId,songId,email);
        }else{
            return "UnAuthorised user Activity";
        }
    }
}
