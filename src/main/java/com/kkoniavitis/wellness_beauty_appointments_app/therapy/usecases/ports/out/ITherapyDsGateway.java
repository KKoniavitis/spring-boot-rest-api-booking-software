package com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.dtos.CreateCountryDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.dtos.CreateTherapyDsDto;

import java.util.List;

public interface ITherapyDsGateway {
    List<CreateTherapyDsDto> findAll();
    List<CreateTherapyDsDto> searchTherapies(String query);
}
