package com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateCountryResponseModel {
    private Long id;
    private String name;
}
