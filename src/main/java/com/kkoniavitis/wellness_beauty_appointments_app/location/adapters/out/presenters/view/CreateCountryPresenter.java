package com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view.resources.CreateCountrySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.models.CreateCountryResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.out.ICountryViewPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateCountryPresenter implements ICountryViewPresenter {

    @Override
    public List<CreateCountrySummaryResponseResource> prepareSuccessView(List<CreateCountryResponseModel> createCountryResponseModels) {
        return createCountryResponseModels.stream()
                .map(this::prepareSuccessView)
                .collect(Collectors.toList());
    }

    @Override
    public CreateCountrySummaryResponseResource prepareSuccessView(CreateCountryResponseModel createUserSummaryResponseModel) {
        return new CreateCountrySummaryResponseResource(createUserSummaryResponseModel.getId(), createUserSummaryResponseModel.getName());
    }

    @Override
    public CreateCountrySummaryResponseResource prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
