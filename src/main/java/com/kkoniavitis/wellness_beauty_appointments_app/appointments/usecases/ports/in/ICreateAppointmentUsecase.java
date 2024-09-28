package com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.in;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.presenters.view.resources.CreateAppointmentSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.AppointmentRequestDto;

import java.util.List;

public interface ICreateAppointmentUsecase {
    List<CreateAppointmentSummaryResponseResource> findAll();

    CreateAppointmentSummaryResponseResource createAppointment(AppointmentRequestDto appointmentRequestDto);
}
