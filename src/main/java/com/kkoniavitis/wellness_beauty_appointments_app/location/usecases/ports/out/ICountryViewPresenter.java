package com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view.resources.CreateCountrySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.models.CreateCountryResponseModel;

import java.util.List;

public interface ICountryViewPresenter {
    List<CreateCountrySummaryResponseResource> prepareSuccessView(List<CreateCountryResponseModel> createCountryResponseModels);
    CreateCountrySummaryResponseResource prepareSuccessView(CreateCountryResponseModel createCountryResponseModel);
    CreateCountrySummaryResponseResource prepareFailView(String error);
}

