package com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateTherapyResponseModel {
    private Long id;
    private String name;
}
