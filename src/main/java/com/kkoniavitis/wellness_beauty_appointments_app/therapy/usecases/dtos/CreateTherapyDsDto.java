package com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateTherapyDsDto {
    private Long id;
    private String name;
}
