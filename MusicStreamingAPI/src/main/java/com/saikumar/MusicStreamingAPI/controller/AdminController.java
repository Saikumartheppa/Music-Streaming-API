package com.saikumar.MusicStreamingAPI.controller;

import com.saikumar.MusicStreamingAPI.model.Admin;
import com.saikumar.MusicStreamingAPI.model.Song;
import com.saikumar.MusicStreamingAPI.model.dtos.admin.AdminSignInInput;
import com.saikumar.MusicStreamingAPI.model.dtos.admin.AdminSignUpOutput;
import com.saikumar.MusicStreamingAPI.service.AdminService;
import com.saikumar.MusicStreamingAPI.service.AuthenticationTokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    //signUp,signIn,signOut functionalities
    @PostMapping("adminSignUp")
    public AdminSignUpOutput adminSignUp(@RequestBody Admin admin){
        return adminService.adminSignUp(admin);
    }
    @PostMapping("adminSignIn")
    public String adminSignIn(@RequestBody AdminSignInInput adminSignInInput){
        return adminService.adminSignIn(adminSignInInput);
    }
    @DeleteMapping("adminSignOut")
    public String adminSignOut(@RequestParam @Valid String email, @RequestParam String tokenValue){
        if(authenticationTokenService.authenticateAdmin(email,tokenValue)){
            return adminService.adminSignOut(email);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    //song CRUD functionalities
    // Adding a song
    @PostMapping("song")
    public String addSong(@RequestBody Song song,@RequestParam String email,@RequestParam String tokenValue){
        if(authenticationTokenService.authenticateAdmin(email,tokenValue)){
            return adminService.addSong(song);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    // fetch song by id
    @GetMapping("song/{songId}")
    public Object getSongById(@PathVariable Long songId,@RequestParam String email,@RequestParam String tokenValue){
        if(authenticationTokenService.authenticateAdmin(email,tokenValue)){
            return adminService.getSongById(songId);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    // fetch all songs
    @GetMapping("songs")
    public Iterable<Song> getSongs(@RequestParam String email,@RequestParam String tokenValue){
        if(authenticationTokenService.authenticateAdmin(email,tokenValue)){
            return adminService.getAllSongs();
        }else{
            throw new RuntimeException("UnAuthorised user Activity");
        }
    }
    @PutMapping("song/title/{songId}/{title}")
    public String UpdateSongById(@PathVariable Long songId,@PathVariable String title,@RequestParam String email,@RequestParam String tokenValue){
        if(authenticationTokenService.authenticateAdmin(email,tokenValue)){
            return adminService.UpdateSongById(songId,title);
        }else{
            return "UnAuthorised user Activity";
        }
    }
    @DeleteMapping("song/{songId}")
    public String deleteSongById(@PathVariable Long songId,@RequestParam String email,@RequestParam String tokenValue){
        if(authenticationTokenService.authenticateAdmin(email,tokenValue)){
            return adminService.deleteSongById(songId);
        }else{
            return "UnAuthorised user Activity";
        }
    }
}
