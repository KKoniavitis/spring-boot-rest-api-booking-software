package com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.out.presenters.view.resources;

import lombok.Data;

@Data
public class CreateJwtAuthenticationResponseResource {
    private String accessToken;
    private String tokenType = "Bearer";

    public CreateJwtAuthenticationResponseResource(String accessToken) {
        this.accessToken = accessToken;
    }
}
