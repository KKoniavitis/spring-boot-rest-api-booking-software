package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models;


import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.AddressEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor @Getter @Setter @Builder
public class CreateUserResponseModel {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private Instant joinedAt;
        private String email;
        private AddressEntity address;
        private String phone;
        private String website;
        private CompanyEntity company;
        private Long postCount;
}

