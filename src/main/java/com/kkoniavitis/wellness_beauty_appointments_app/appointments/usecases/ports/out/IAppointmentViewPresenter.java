package com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.appointments.adapters.out.presenters.view.resources.CreateAppointmentSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.appointments.usecases.models.CreateAppointmentResponseModel;

import java.util.List;

public interface IAppointmentViewPresenter {
    List<CreateAppointmentSummaryResponseResource> prepareSuccessView(List<CreateAppointmentResponseModel> createAppointmentResponseModels);
    CreateAppointmentSummaryResponseResource prepareSuccessView(CreateAppointmentResponseModel createAppointmentResponseModel);
    CreateAppointmentSummaryResponseResource prepareFailView(String error);
}
