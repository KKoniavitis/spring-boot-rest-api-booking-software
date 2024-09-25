package com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.presenters.view.resources.CreateCompanySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.models.CreateCompanyResponseModel;

import java.util.List;

public interface ICompanyViewPresenter {
    List<CreateCompanySummaryResponseResource> prepareSuccessView(List<CreateCompanyResponseModel> createCompanyResponseModels);
    CreateCompanySummaryResponseResource prepareSuccessView(CreateCompanyResponseModel createCompanyResponseModel);
    CreateCompanySummaryResponseResource prepareFailView(String error);
}
