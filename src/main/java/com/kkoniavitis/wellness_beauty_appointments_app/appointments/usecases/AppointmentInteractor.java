package com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.presenters.view.resources.CreateAppointmentSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.AppointmentRequestDto;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.dtos.CreateAppointmentDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.models.CreateAppointmentResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.in.ICreateAppointmentUsecase;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out.IAppointmentDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out.IAppointmentViewPresenter;
import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.entities.CompanyEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.repositories.ICompanyRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.dtos.CreateCompanyDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out.ICompanyDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.entities.UserEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.repositories.IUserRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos.CreateUserDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IUserDsGateway;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentInteractor implements ICreateAppointmentUsecase {

    final IAppointmentDsGateway appointmentDsGateway;
    private final IUserDsGateway userDsGateway;
    private final ICompanyDsGateway companyDsGateway;
    final IAppointmentViewPresenter appointmentViewPresenter;

    public AppointmentInteractor(IAppointmentDsGateway appointmentDsGateway, IUserDsGateway userDsGateway, ICompanyDsGateway companyDsGateway, IAppointmentViewPresenter appointmentViewPresenter) {
        this.appointmentDsGateway = appointmentDsGateway;
        this.userDsGateway = userDsGateway;
        this.companyDsGateway = companyDsGateway;
        this.appointmentViewPresenter = appointmentViewPresenter;
    }

    @Override
    public List<CreateAppointmentSummaryResponseResource> findAll() {
        List<CreateAppointmentDsDto> allAppointments = appointmentDsGateway.findAll();
        List<CreateAppointmentResponseModel> appointmentResponseModels = new ArrayList<>();
        for (CreateAppointmentDsDto appointmentDsDto : allAppointments) {
            CreateAppointmentResponseModel appointmentResponseModel =
                    CreateAppointmentResponseModel.builder()
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
        CreateUserDsDto user = userDsGateway.getUserProfile(appointmentRequestDto.getUserId());
        CreateCompanyDsDto company = companyDsGateway.getCompanyById(Long.valueOf(appointmentRequestDto.getCompanyId()));
        CreateAppointmentDsDto appointmentDsDto =
                CreateAppointmentDsDto.builder()
                        .user(user.getUsername())
                        .company(company.getId())
                        .appointmentDate(appointmentRequestDto.getAppointmentDate())
                        .build();

        appointmentDsGateway.createAppointment(appointmentDsDto);

        CreateAppointmentResponseModel createAppointmentResponseModel =
                CreateAppointmentResponseModel.builder()
                        .user(appointmentDsDto.getUser())
                        .company(Long.valueOf(appointmentRequestDto.getCompanyId()))
                        .appointmentDate(appointmentRequestDto.getAppointmentDate())
                        .build();

        return appointmentViewPresenter.prepareSuccessView(createAppointmentResponseModel);
    }
}
