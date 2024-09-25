package com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.entities.CompanyEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.gateways.ds.repositories.ICompanyRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.dtos.CreateCompanyDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out.ICompanyDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.gateways.ds.entities.CountryEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.location.adapters.out.gateways.ds.repositories.ICountryRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.location.usecases.dtos.CreateCountryDsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyDsGateway implements ICompanyDsGateway {

    private final ICompanyRepository companyRepository;

    @Override
    public List<CreateCompanyDsDto> findAll() {
        List<CompanyEntity> companyEntities = companyRepository.findAll();

        return companyEntities.stream()
                .map(company -> CreateCompanyDsDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .location(company.getLocation())
                        .category(company.getCategory())
                        .description(company.getDescription())
                        .imageUrl(company.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CreateCompanyDsDto> searchCompanies(String query) {
        List<CompanyEntity> companyEntities = companyRepository.findByNameContainingIgnoreCase(query);

        return companyEntities.stream()
                .map(company -> CreateCompanyDsDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .location(company.getLocation())
                        .category(company.getCategory())
                        .description(company.getDescription())
                        .imageUrl(company.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }
}
