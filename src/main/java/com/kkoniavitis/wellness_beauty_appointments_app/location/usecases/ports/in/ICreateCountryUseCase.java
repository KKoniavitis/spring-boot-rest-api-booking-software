package com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.in;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.presenters.view.resources.CreateCountrySummaryResponseResource;

import java.util.List;

public interface ICreateCountryUseCase {

    List<CreateCountrySummaryResponseResource> findAll();
    List<CreateCountrySummaryResponseResource> searchCountries(String query);
}
