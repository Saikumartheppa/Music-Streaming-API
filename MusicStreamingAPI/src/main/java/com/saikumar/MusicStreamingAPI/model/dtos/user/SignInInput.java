package com.saikumar.MusicStreamingAPI.model.dtos.user;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInInput {
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$")
    private String email;
    private String password;
}
