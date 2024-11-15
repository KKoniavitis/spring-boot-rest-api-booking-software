package com.kkoniavitis.wellness_beauty_appointments_app.company.usecases;

import com.kkoniavitis.wellness_beauty_appointments_app.company.adapters.out.presenters.view.resources.CreateCompanySummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.dtos.CreateCompanyDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.models.CreateCompanyResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.in.ICreateCompanyUsecase;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out.ICompanyDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.company.usecases.ports.out.ICompanyViewPresenter;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos.CreateUserDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserSummaryResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyInteractor implements ICreateCompanyUsecase {
    final ICompanyDsGateway companyDsGateway;
    final ICompanyViewPresenter companyViewPresenter;

    public CompanyInteractor(ICompanyDsGateway companyDsGateway, ICompanyViewPresenter companyViewPresenter) {
        this.companyDsGateway = companyDsGateway;
        this.companyViewPresenter = companyViewPresenter;
    }

    @Override
    public CreateCompanySummaryResponseResource getCompanyById(Long id) {
        CreateCompanyDsDto companyEntity = companyDsGateway.getCompanyById(id);
        CreateCompanyResponseModel company =
                CreateCompanyResponseModel.builder()
                        .id(companyEntity.getId())
                        .name(companyEntity.getName())
                        .location(companyEntity.getLocation())
                        .category(companyEntity.getCategory())
                        .latitude(companyEntity.getLatitude())
                        .longitude(companyEntity.getLongitude())
                        .description(companyEntity.getDescription())
                        .imageUrl(companyEntity.getImageUrl())
                        .build();
        return companyViewPresenter.prepareSuccessView(company);
    }

    @Override
    public List<CreateCompanySummaryResponseResource> findAll() {
        List<CreateCompanyDsDto> allCompanies = companyDsGateway.findAll();
        List<CreateCompanyResponseModel> companyResponseModels = new ArrayList<>();
        for (CreateCompanyDsDto company : allCompanies) {
            CreateCompanyResponseModel companyModel =
                    CreateCompanyResponseModel.builder()
                            .id(company.getId())
                            .name(company.getName())
                            .location(company.getLocation())
                            .category(company.getCategory())
                            .latitude(company.getLatitude())
                            .longitude(company.getLongitude())
                            .description(company.getDescription())
                            .imageUrl(company.getImageUrl())
                            .build();
            companyResponseModels.add(companyModel);
        }
        return companyViewPresenter.prepareSuccessView(companyResponseModels);
    }

    @Override
    public List<CreateCompanySummaryResponseResource> searchCompanies(String query) {
        if (query == null || query.isEmpty()) {
            List<CreateCompanyDsDto> allCompanies = companyDsGateway.findAll();
            List<CreateCompanyResponseModel> companyResponseModels = new ArrayList<>();
            for (CreateCompanyDsDto company : allCompanies) {
                CreateCompanyResponseModel companyResponseModel =
                        CreateCompanyResponseModel.builder()
                                .id(company.getId())
                                .name(company.getName())
                                .location(company.getLocation())
                                .category(company.getCategory())
                                .latitude(company.getLatitude())
                                .longitude(company.getLongitude())
                                .description(company.getDescription())
                                .imageUrl(company.getImageUrl())
                                .build();
                companyResponseModels.add(companyResponseModel);
            }
            return companyViewPresenter.prepareSuccessView(companyResponseModels);
        } else {
            List<CreateCompanyDsDto> allCompanies = companyDsGateway.searchCompanies(query);  // Fetch filtered countries
            List<CreateCompanyResponseModel> companyResponseModels = new ArrayList<>();
            for (CreateCompanyDsDto company : allCompanies) {
                CreateCompanyResponseModel companyResponseModel =
                        CreateCompanyResponseModel.builder()
                                .id(company.getId())
                                .name(company.getName())
                                .location(company.getLocation())
                                .category(company.getCategory())
                                .latitude(company.getLatitude())
                                .longitude(company.getLongitude())
                                .description(company.getDescription())
                                .imageUrl(company.getImageUrl())
                                .build();
                companyResponseModels.add(companyResponseModel);
            }
            return companyViewPresenter.prepareSuccessView(companyResponseModels);
        }

    }
}
