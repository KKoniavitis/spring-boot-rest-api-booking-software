package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos;


import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.AddressEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.Company2Entity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserDsDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Instant joinedAt;
    private String email;
    private AddressEntity address;
    private String phone;
    private String website;
    private Company2Entity company;
    private Long postCount;
    private List<RoleEntity> roles;
}
