package com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.presenters.view.resources;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateTherapySummaryResponseResource {
    private Long id;
    private String name;
}
