package com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateCompanyDsDto {
    private Long id;
    private String name;
    private String location;
    private String category;
    private Double latitude;
    private Double longitude;
    private String description;
    private String imageUrl;
}
