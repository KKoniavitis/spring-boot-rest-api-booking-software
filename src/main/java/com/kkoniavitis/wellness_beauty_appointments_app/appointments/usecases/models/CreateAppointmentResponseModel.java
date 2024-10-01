package com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.models;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.entities.CompanyEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreateAppointmentResponseModel {

    private String user; // Reference to the user who booked the appointment

    private Long company;

    private LocalDateTime appointmentDate;
}
