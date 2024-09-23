package com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.gateways.ds;

import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.gateways.ds.entities.CountryEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.gateways.ds.repositories.ICountryRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.dtos.CreateCountryDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.ports.out.ICountryDsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryDsGateway implements ICountryDsGateway {

    private final ICountryRepository countryRepository;

    @Override
    public List<CreateCountryDsDto> findAll() {
        List<CountryEntity> countryEntities = countryRepository.findAll();

        return countryEntities.stream()
                .map(country -> CreateCountryDsDto.builder()
                        .id(country.getId())
                        .name(country.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CreateCountryDsDto> searchCountries(String query) {
        List<CountryEntity> countryEntities = countryRepository.findByNameContainingIgnoreCase(query);

        return countryEntities.stream()
                .map(country -> CreateCountryDsDto.builder()
                        .id(country.getId())
                        .name(country.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
