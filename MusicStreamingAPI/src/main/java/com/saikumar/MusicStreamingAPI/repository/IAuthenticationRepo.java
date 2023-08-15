package com.saikumar.MusicStreamingAPI.repository;

import com.saikumar.MusicStreamingAPI.model.Admin;
import com.saikumar.MusicStreamingAPI.model.AuthenticationToken;
import com.saikumar.MusicStreamingAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo  extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findFirstByTokenValue(String tokenValue);

    AuthenticationToken findFirstByUser(User user);

    AuthenticationToken findFirstByAdmin(Admin admin);
}
