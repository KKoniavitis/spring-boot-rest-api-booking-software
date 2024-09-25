package com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateCompanyResponseModel {
    private Long id;
    private String name;
    private String location;
    private String category;
    private String description;
    private String imageUrl;
}
