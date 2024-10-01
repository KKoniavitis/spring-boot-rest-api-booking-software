package com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.presenters.view;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.presenters.view.resources.CreateAppointmentSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.models.CreateAppointmentResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out.IAppointmentViewPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateAppointmentPresenter implements IAppointmentViewPresenter {
    @Override
    public List<CreateAppointmentSummaryResponseResource> prepareSuccessView(List<CreateAppointmentResponseModel> createAppointmentResponseModels) {
        return createAppointmentResponseModels.stream()
                .map(this::prepareSuccessView)
                .collect(Collectors.toList());
    }

    @Override
    public CreateAppointmentSummaryResponseResource prepareSuccessView(CreateAppointmentResponseModel createAppointmentResponseModel) {
        return new CreateAppointmentSummaryResponseResource(createAppointmentResponseModel.getUser(),createAppointmentResponseModel.getCompany(),
                createAppointmentResponseModel.getAppointmentDate());
    }

    @Override
    public CreateAppointmentSummaryResponseResource prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }

}
