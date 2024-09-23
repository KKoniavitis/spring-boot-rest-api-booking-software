package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserSummaryResponseModel;

import java.util.List;

public interface IUserViewPresenter {

    List<CreateUserSummaryResponseResource> prepareSuccessView(List<CreateUserSummaryResponseModel> createUserSummaryResponseModels);
    CreateUserSummaryResponseResource prepareSuccessView(CreateUserSummaryResponseModel createUserSummaryResponseDto);

    CreateUserSummaryResponseResource prepareFailView(String error);
}
