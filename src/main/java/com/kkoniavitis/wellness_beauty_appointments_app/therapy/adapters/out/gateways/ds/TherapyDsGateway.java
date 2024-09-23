package com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.gateways.ds;

import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.gateways.ds.entities.TherapyEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.adapters.out.gateways.ds.repositories.ITherapyRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.dtos.CreateTherapyDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.therapy.usecases.ports.out.ITherapyDsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TherapyDsGateway implements ITherapyDsGateway {

    private final ITherapyRepository therapyRepository;

    @Override
    public List<CreateTherapyDsDto> findAll() {
        List<TherapyEntity> therapyEntities = therapyRepository.findAll();

        return therapyEntities.stream()
                .map(therapy -> CreateTherapyDsDto.builder()
                        .id(therapy.getId())
                        .name(therapy.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CreateTherapyDsDto> searchTherapies(String query) {
        List<TherapyEntity> therapyEntities = therapyRepository.findByNameContainingIgnoreCase(query);

        return therapyEntities.stream()
                .map(therapy -> CreateTherapyDsDto.builder()
                        .id(therapy.getId())
                        .name(therapy.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
