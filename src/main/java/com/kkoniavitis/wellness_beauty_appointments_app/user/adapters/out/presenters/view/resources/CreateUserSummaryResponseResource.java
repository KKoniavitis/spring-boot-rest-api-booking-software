package com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserSummaryResponseResource {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}
