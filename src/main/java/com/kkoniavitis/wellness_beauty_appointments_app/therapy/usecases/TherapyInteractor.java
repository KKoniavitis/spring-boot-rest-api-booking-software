package com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases;

import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.presenters.view.resources.CreateTherapySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.dtos.CreateTherapyDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.models.CreateTherapyResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.in.ICreateTherapyUseCase;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.out.ITherapyDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.out.ITherapyViewPresenter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TherapyInteractor implements ICreateTherapyUseCase {
    final ITherapyDsGateway therapyDsGateway;
    final ITherapyViewPresenter therapyViewPresenter;

    public TherapyInteractor(ITherapyDsGateway therapyDsGateway, ITherapyViewPresenter therapyViewPresenter) {
        this.therapyDsGateway = therapyDsGateway;
        this.therapyViewPresenter = therapyViewPresenter;
    }

    @Override
    public List<CreateTherapySummaryResponseResource> findAll() {
        List<CreateTherapyDsDto> allTherapies = therapyDsGateway.findAll();
        List<CreateTherapyResponseModel> therapyResponseModels = new ArrayList<>();
        for (CreateTherapyDsDto therapy : allTherapies) {
            CreateTherapyResponseModel therapyModel =
                    CreateTherapyResponseModel.builder()
                            .id(therapy.getId())
                            .name(therapy.getName())
                            .build();
            therapyResponseModels.add(therapyModel);
        }
        return therapyViewPresenter.prepareSuccessView(therapyResponseModels);
    }

    @Override
    public List<CreateTherapySummaryResponseResource> searchTherapies(String query) {
        if (query == null || query.isEmpty()) {
            List<CreateTherapyDsDto> allTherapies = therapyDsGateway.findAll();
            List<CreateTherapyResponseModel> therapyResponseModels = new ArrayList<>();
            for (CreateTherapyDsDto therapy : allTherapies) {
                CreateTherapyResponseModel therapyModel =
                        CreateTherapyResponseModel.builder()
                                .id(therapy.getId())
                                .name(therapy.getName())
                                .build();
                therapyResponseModels.add(therapyModel);
            }
            return therapyViewPresenter.prepareSuccessView(therapyResponseModels);
        } else {
            List<CreateTherapyDsDto> allTherapies = therapyDsGateway.searchTherapies(query);
            List<CreateTherapyResponseModel> therapyResponseModels = new ArrayList<>();
            for (CreateTherapyDsDto therapy : allTherapies) {
                CreateTherapyResponseModel therapyModel =
                        CreateTherapyResponseModel.builder()
                                .id(therapy.getId())
                                .name(therapy.getName())
                                .build();
                therapyResponseModels.add(therapyModel);
            }
            return therapyViewPresenter.prepareSuccessView(therapyResponseModels);
        }

    }
}
