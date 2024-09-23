package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserSummaryResponseModel {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}
