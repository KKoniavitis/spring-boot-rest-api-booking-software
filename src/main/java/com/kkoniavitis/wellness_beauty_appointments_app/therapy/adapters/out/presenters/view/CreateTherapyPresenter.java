package com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.presenters.view;

import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.presenters.view.resources.CreateTherapySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.models.CreateTherapyResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.out.ITherapyViewPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateTherapyPresenter implements ITherapyViewPresenter {

    @Override
    public List<CreateTherapySummaryResponseResource> prepareSuccessView(List<CreateTherapyResponseModel> createTherapyResponseModels) {
        return createTherapyResponseModels.stream()
                .map(this::prepareSuccessView)
                .collect(Collectors.toList());
    }

    @Override
    public CreateTherapySummaryResponseResource prepareSuccessView(CreateTherapyResponseModel createTherapyResponseModels) {
        return new CreateTherapySummaryResponseResource(createTherapyResponseModels.getId(), createTherapyResponseModels.getName());
    }

    @Override
    public CreateTherapySummaryResponseResource prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
