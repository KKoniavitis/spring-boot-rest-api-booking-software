package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models;



import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.AddressEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter @Builder
public class CreateUserRequestModel {
    private Long id;
    private String username;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
    private AddressEntity address;
    private String phone;
    private String website;
    private CompanyEntity company;

}
