package com.saikumar.MusicStreamingAPI.service;

import com.saikumar.MusicStreamingAPI.model.Admin;
import com.saikumar.MusicStreamingAPI.model.AuthenticationToken;
import com.saikumar.MusicStreamingAPI.model.Song;
import com.saikumar.MusicStreamingAPI.model.dtos.admin.AdminSignInInput;
import com.saikumar.MusicStreamingAPI.model.dtos.admin.AdminSignUpOutput;
import com.saikumar.MusicStreamingAPI.repository.IAdminRepo;
import com.saikumar.MusicStreamingAPI.service.emailUtility.EmailHandler;
import com.saikumar.MusicStreamingAPI.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;
    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @Autowired
    SongService songService;
    public AdminSignUpOutput adminSignUp(Admin admin) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;
        // check the user email  if null
        String newEmail = admin.getAdminEmail();
        if(newEmail == null){
            signUpStatus = false;
            signUpStatusMessage = "The SoundFusion admin email should not be null";
            return new AdminSignUpOutput(signUpStatus,signUpStatusMessage);
        }
        // check if already exists??
         Admin existingAdmin = adminRepo.findFirstByAdminEmail(newEmail);
        if(existingAdmin != null){
            signUpStatus = false;
            signUpStatusMessage = "The Sound Fusion Admin already exists!! Try with signIn";
            return new AdminSignUpOutput(signUpStatus,signUpStatusMessage);
        }
        // encrypt the password of user by hashing
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(admin.getAdminPassword());
            // set the encrypted password of user and save the encrypted password
            admin.setAdminPassword(encryptedPassword);
            adminRepo.save(admin);
            return new AdminSignUpOutput(signUpStatus,"Admin registered Successfully!!");
        }catch(Exception e){
            signUpStatus = false;
            signUpStatusMessage = "Internal error occurred during sign up";
            return new AdminSignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String adminSignIn(AdminSignInInput adminSignInInput) {
        String signInInputMessage = null;
        String adminEmail = adminSignInInput.getEmail();
        // check the email is null
        if(adminEmail == null){
            signInInputMessage = "Admin email should not be null";
            return signInInputMessage;
        }
        //check user exist or not ??
        Admin existingAdmin = adminRepo.findFirstByAdminEmail(adminEmail);
        if(existingAdmin == null){
            signInInputMessage = "No such Admin exist!!! try with signUp";
            return signInInputMessage;
        }
        // if exist...hash the password && check the password matches with original password
        try{
            String encryptedPassword = PasswordEncrypter.encryptPassword(adminSignInInput.getPassword());
            if(encryptedPassword.equals(existingAdmin.getAdminPassword())){
                AuthenticationToken authenticationToken = new AuthenticationToken(existingAdmin);
                authenticationTokenService.save(authenticationToken);
                EmailHandler.sendEmail(adminEmail,"Java testing mail",authenticationToken.getTokenValue());
                signInInputMessage = "Token sent to your registered mail";
            }else{
                signInInputMessage = "Invalid credentials";
            }
            return signInInputMessage;
        }catch (Exception e){
            return "Internal error occurred during signIn";
        }
    }

    public String adminSignOut(String email) {
        Admin admin = adminRepo.findFirstByAdminEmail(email);
        AuthenticationToken authenticationToken = authenticationTokenService.findFirstByAdmin(admin);
        authenticationTokenService.removeAdminToken(authenticationToken);
        return "Admin signed out successfully!!";
    }

    public String addSong(Song song) {

        Song existingSong = songService.findFirstBySongTitle(song.getSongTitle());
        if(existingSong == null){
            return songService.addSong(song);
        }else{
            return "Song with Title" + " : " +song.getSongTitle() +" already exists!!! try with different title!!";
        }
    }

    public Song getSongById(Long sId) {
        Song song = songService.getSongById(sId);
        return song;
    }

    public Iterable<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    public String UpdateSongById(Long songId, String title) {
           return songService.UpdateSongById(songId,title);
    }

    public String deleteSongById(Long sId) {
        Song existingSong = songService.getSongById(sId);
        if(existingSong != null){
            songService.removeSong(existingSong);
            return "Song : "+sId+" deleted successfully!!!";
        }else{
            return "No such song : "+sId+" exists";
        }
    }
}
