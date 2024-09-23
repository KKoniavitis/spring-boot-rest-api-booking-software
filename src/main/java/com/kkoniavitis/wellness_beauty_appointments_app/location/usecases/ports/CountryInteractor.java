package com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view.resources.CreateCountrySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.dtos.CreateCountryDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.models.CreateCountryResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.in.ICreateCountryUseCase;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.out.ICountryDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.out.ICountryViewPresenter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryInteractor implements ICreateCountryUseCase {
    final ICountryDsGateway countryDsGateway;
    final ICountryViewPresenter countryViewPresenter;

    public CountryInteractor(ICountryDsGateway countryDsGateway, ICountryViewPresenter countryViewPresenter) {
        this.countryDsGateway = countryDsGateway;
        this.countryViewPresenter = countryViewPresenter;
    }

    @Override
    public List<CreateCountrySummaryResponseResource> findAll() {
        List<CreateCountryDsDto> allCountries = countryDsGateway.findAll();
        List<CreateCountryResponseModel> countryResponseModels = new ArrayList<>();
        for (CreateCountryDsDto country : allCountries) {
            CreateCountryResponseModel countryModel =
                    CreateCountryResponseModel.builder()
                            .id(country.getId())
                            .name(country.getName())
                            .build();
            countryResponseModels.add(countryModel);
        }
        return countryViewPresenter.prepareSuccessView(countryResponseModels);
    }

    @Override
    public List<CreateCountrySummaryResponseResource> searchCountries(String query) {
        if (query == null || query.isEmpty()) {
            List<CreateCountryDsDto> allCountries = countryDsGateway.findAll();
            List<CreateCountryResponseModel> countryResponseModels = new ArrayList<>();
            for (CreateCountryDsDto country : allCountries) {
                CreateCountryResponseModel countryModel =
                        CreateCountryResponseModel.builder()
                                .id(country.getId())
                                .name(country.getName())
                                .build();
                countryResponseModels.add(countryModel);
            }
            return countryViewPresenter.prepareSuccessView(countryResponseModels);
        } else {
            List<CreateCountryDsDto> allCountries = countryDsGateway.searchCountries(query);  // Fetch filtered countries
            List<CreateCountryResponseModel> countryResponseModels = new ArrayList<>();
            for (CreateCountryDsDto country : allCountries) {
                CreateCountryResponseModel countryModel =
                        CreateCountryResponseModel.builder()
                                .id(country.getId())
                                .name(country.getName())
                                .build();
                countryResponseModels.add(countryModel);
            }
            return countryViewPresenter.prepareSuccessView(countryResponseModels);
        }

    }

}
