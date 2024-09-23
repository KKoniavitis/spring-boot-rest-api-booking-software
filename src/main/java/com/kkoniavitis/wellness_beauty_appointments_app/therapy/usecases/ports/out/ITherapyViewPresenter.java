package com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view.resources.CreateCountrySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.models.CreateCountryResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.presenters.view.resources.CreateTherapySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.models.CreateTherapyResponseModel;

import java.util.List;

public interface ITherapyViewPresenter {
    List<CreateTherapySummaryResponseResource> prepareSuccessView(List<CreateTherapyResponseModel> createTherapyResponseModels);
    CreateTherapySummaryResponseResource prepareSuccessView(CreateTherapyResponseModel createTherapyResponseModel);
    CreateTherapySummaryResponseResource prepareFailView(String error);
}
