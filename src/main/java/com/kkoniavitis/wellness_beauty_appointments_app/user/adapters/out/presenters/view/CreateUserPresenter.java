package com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view;

import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserSummaryResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IUserViewPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateUserPresenter implements IUserViewPresenter {

    @Override
    public List<CreateUserSummaryResponseResource> prepareSuccessView(List<CreateUserSummaryResponseModel> createUserSummaryResponseModels) {
        return createUserSummaryResponseModels.stream()
                .map(this::prepareSuccessView)
                .collect(Collectors.toList());
    }
    @Override
    public CreateUserSummaryResponseResource prepareSuccessView(CreateUserSummaryResponseModel createUserSummaryResponseModel) {
        return new CreateUserSummaryResponseResource(createUserSummaryResponseModel.getId(), createUserSummaryResponseModel.getUsername(),
                createUserSummaryResponseModel.getFirstName(), createUserSummaryResponseModel.getLastName());
    }

    @Override
    public CreateUserSummaryResponseResource prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
