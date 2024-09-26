package com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.presenters.view;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.presenters.view.resources.CreateCompanySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.models.CreateCompanyResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out.ICompanyViewPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateCompanyPresenter implements ICompanyViewPresenter {
    @Override
    public List<CreateCompanySummaryResponseResource> prepareSuccessView(List<CreateCompanyResponseModel> createCompanyResponseModels) {
        return createCompanyResponseModels.stream()
                .map(this::prepareSuccessView)
                .collect(Collectors.toList());
    }

    @Override
    public CreateCompanySummaryResponseResource prepareSuccessView(CreateCompanyResponseModel createCompanyResponseModel) {
        return new CreateCompanySummaryResponseResource(createCompanyResponseModel.getId(), createCompanyResponseModel.getName(), createCompanyResponseModel.getLocation(),
                createCompanyResponseModel.getDescription(), createCompanyResponseModel.getLatitude(), createCompanyResponseModel.getLongitude(),createCompanyResponseModel.getCategory(), createCompanyResponseModel.getImageUrl());
    }

    @Override
    public CreateCompanySummaryResponseResource prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
