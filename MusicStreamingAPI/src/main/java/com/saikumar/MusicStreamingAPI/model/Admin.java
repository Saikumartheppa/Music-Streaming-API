package com.saikumar.MusicStreamingAPI.model;

import com.saikumar.MusicStreamingAPI.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String adminName;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@soundfusionadmin\\.com$")
    @Column(unique = true)
    private String adminEmail;
    @NotBlank
    private String adminPassword;
    @Enumerated(EnumType.STRING)
    private Gender adminGender;
}
