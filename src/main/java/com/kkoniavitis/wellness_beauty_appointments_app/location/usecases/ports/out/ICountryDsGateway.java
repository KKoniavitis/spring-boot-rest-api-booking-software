package com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.dtos.CreateCountryDsDto;

import java.util.List;

public interface ICountryDsGateway {

    List<CreateCountryDsDto> findAll();
    List<CreateCountryDsDto> searchCountries(String query);
}
