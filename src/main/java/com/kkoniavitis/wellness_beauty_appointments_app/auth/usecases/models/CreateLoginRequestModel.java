package com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoginRequestModel {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
