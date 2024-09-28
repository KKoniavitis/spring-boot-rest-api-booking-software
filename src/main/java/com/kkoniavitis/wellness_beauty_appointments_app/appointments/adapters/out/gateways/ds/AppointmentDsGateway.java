package com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.gateways.ds;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.gateways.ds.entities.AppointmentEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.gateways.ds.repositories.IAppointmentRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.AppointmentRequestDto;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.CreateAppointmentDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out.IAppointmentDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.entities.CompanyEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.repositories.ICompanyRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.dtos.CreateCompanyDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out.ICompanyDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentDsGateway implements IAppointmentDsGateway {

    private final IAppointmentRepository appointmentRepository;

    @Override
    public List<CreateAppointmentDsDto> findAll() {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findAll();

        return appointmentEntities.stream()
                .map(appointment -> CreateAppointmentDsDto.builder()
                        .id(appointment.getId())
                        .user(appointment.getUser())
                        .company(appointment.getCompany())
                        .appointmentDate(appointment.getAppointmentDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void createAppointment(CreateAppointmentDsDto appointment) {
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(appointment.getId())
                .user(appointment.getUser())
                .company(appointment.getCompany())
                .appointmentDate(appointment.getAppointmentDate())
                .build();
        appointmentRepository.save(appointmentEntity);
    }
}
