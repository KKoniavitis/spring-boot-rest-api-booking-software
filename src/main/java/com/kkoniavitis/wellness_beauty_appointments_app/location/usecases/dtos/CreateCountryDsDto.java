package com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateCountryDsDto {
    private Long id;
    private String name;
}