package com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.presenters.view.resources.CreateAppointmentSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.AppointmentRequestDto;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.CreateAppointmentDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.models.CreateAppointmentResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.in.ICreateAppointmentUsecase;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out.IAppointmentDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out.IAppointmentViewPresenter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentInteractor implements ICreateAppointmentUsecase {

    final IAppointmentDsGateway appointmentDsGateway;
    final IAppointmentViewPresenter appointmentViewPresenter;

    public AppointmentInteractor(IAppointmentDsGateway appointmentDsGateway, IAppointmentViewPresenter appointmentViewPresenter) {
        this.appointmentDsGateway = appointmentDsGateway;
        this.appointmentViewPresenter = appointmentViewPresenter;
    }

    @Override
    public List<CreateAppointmentSummaryResponseResource> findAll() {
        List<CreateAppointmentDsDto> allAppointments = appointmentDsGateway.findAll();
        List<CreateAppointmentResponseModel> appointmentResponseModels = new ArrayList<>();
        for (CreateAppointmentDsDto appointmentDsDto : allAppointments) {
            CreateAppointmentResponseModel appointmentResponseModel =
                    CreateAppointmentResponseModel.builder()
                            .id(appointmentDsDto.getId())
                            .user(appointmentDsDto.getUser())
                            .company(appointmentDsDto.getCompany())
                            .appointmentDate(appointmentDsDto.getAppointmentDate())
                            .build();
            appointmentResponseModels.add(appointmentResponseModel);
        }
        return appointmentViewPresenter.prepareSuccessView(appointmentResponseModels);
    }

    @Override
    public CreateAppointmentSummaryResponseResource createAppointment(AppointmentRequestDto appointmentRequestDto) {
        CreateAppointmentDsDto appointmentDsDto =
                CreateAppointmentDsDto.builder()
                        .user(appointmentRequestDto.getUserId())
                        .company(appointmentRequestDto.getCompanyId())
                        .appointmentDate(appointmentRequestDto.getAppointmentDate())
                        .build();

        appointmentDsGateway.createAppointment(appointmentDsDto);

        CreateAppointmentResponseModel createAppointmentResponseModel =
                CreateAppointmentResponseModel.builder()
                        .user(appointmentRequestDto.getUserId())
                        .company(appointmentRequestDto.getCompanyId())
                        .appointmentDate(appointmentRequestDto.getAppointmentDate())
                        .build();

        return appointmentViewPresenter.prepareSuccessView(createAppointmentResponseModel);
    }
}
