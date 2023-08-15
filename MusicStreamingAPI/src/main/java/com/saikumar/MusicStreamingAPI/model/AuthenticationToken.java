package com.saikumar.MusicStreamingAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTimeStamp;
    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User user;
    @OneToOne
    @JoinColumn(name = "fk_admin_id")
    Admin admin;
    // create a parameterised constructor for user

    public AuthenticationToken(User user) {
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTimeStamp = LocalDateTime.now();
    }
    public AuthenticationToken(Admin admin) {
        this.admin = admin;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTimeStamp = LocalDateTime.now();
    }
}
