package com.saikumar.MusicStreamingAPI.model.dtos.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminSignUpOutput {
    private Boolean signupOutputStatus;
    private String signupOutputMessage;

}
