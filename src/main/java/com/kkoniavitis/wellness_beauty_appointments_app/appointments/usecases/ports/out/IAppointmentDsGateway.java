package com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.CreateAppointmentDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos.CreateUserDsDto;

import java.util.List;

public interface IAppointmentDsGateway {
    List<CreateAppointmentDsDto> findAll();

    void createAppointment(CreateAppointmentDsDto appointment);
}
