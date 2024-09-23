package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out;

import com.kkoniavitis.wellness_beauty_appointments_app.auth.domains.UserPrincipal;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.entities.UserEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos.CreateUserDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserRequestModel;

import java.util.List;
import java.util.Optional;

public interface IUserDsGateway {
    List<CreateUserDsDto> getAllUsers();
    CreateUserDsDto getCurrentUser(UserPrincipal currentUser);
    CreateUserDsDto getUserProfile(String username);
    void addUser(CreateUserDsDto user);
    CreateUserDsDto updateUser(CreateUserRequestModel newUser, String username, UserPrincipal currentUser);
    void deleteUser(String username, UserPrincipal currentUser);
    void giveAdmin(String username);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
    Optional<UserEntity> findById(Long id);
    void removeAdmin(String username);
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
    CreateUserDsDto findByName(String name);
    boolean existsByEmail(String email);
    CreateUserDsDto save(CreateUserDsDto requestModel);
    Long count();
}
