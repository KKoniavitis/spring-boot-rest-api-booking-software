package com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds;

import com.kkoniavitis.wellness_beauty_appointments_app.auth.domains.UserPrincipal;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.AccessDeniedException;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.AppException;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.BadRequestException;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.entities.UserEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.repositories.IRoleRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.repositories.IUserRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleName;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos.CreateUserDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IUserDsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDsGateway implements IUserDsGateway {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    @Override
    public List<CreateUserDsDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userEntities.stream()
                .map(user -> CreateUserDsDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .phone(user.getPhone())
                        .website(user.getWebsite())
                        .company(user.getCompany())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CreateUserDsDto getCurrentUser(UserPrincipal currentUser) {
        UserEntity signedUser = userRepository.getUser(currentUser);
        return CreateUserDsDto.builder()
                .id(signedUser.getId())
                .username(signedUser.getUsername())
                .firstName(signedUser.getFirstName())
                .lastName(signedUser.getLastName())
                .build();
    }

    @Override
    public CreateUserDsDto getUserProfile(String username) {
        UserEntity user = userRepository.getUserByName(username);
        return CreateUserDsDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .company(user.getCompany())
                .build();
    }

    @Override
    public void addUser(CreateUserDsDto createUserDsDto) {
        if (userRepository.existsByUsername(createUserDsDto.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }
        if (userRepository.existsByEmail(createUserDsDto.getEmail())) {
            throw new BadRequestException("Email is already taken");
        }
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(
                roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        createUserDsDto.setRoles(roles);
        UserEntity user = UserEntity.builder()
                .id(createUserDsDto.getId())
                .username(createUserDsDto.getUsername())
                .firstName(createUserDsDto.getFirstName())
                .lastName(createUserDsDto.getLastName())
                .email(createUserDsDto.getEmail())
                .address(createUserDsDto.getAddress())
                .phone(createUserDsDto.getPhone())
                .website(createUserDsDto.getWebsite())
                .company(createUserDsDto.getCompany())
                .roles(roles)
                .build();
        userRepository.save(user);
    }

    @Override
    public CreateUserDsDto updateUser(CreateUserRequestModel newUser, String username, UserPrincipal currentUser) {
        UserEntity user = userRepository.getUserByName(username);
        userRepository.save(user);
        return CreateUserDsDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .company(user.getCompany())
                .build();

    }

    @Override
    public void deleteUser(String username, UserPrincipal currentUser) {
        UserEntity user = userRepository.findByUsername(username);
        if (!user.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException("You don't have permission to delete profile of: " + username);
        }
        userRepository.deleteById(user.getId());
    }

    @Override
    public void giveAdmin(String username) {
        UserEntity user = userRepository.getUserByName(username);
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User role not set")));
        roles.add(
                roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public Optional<UserEntity> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public void removeAdmin(String username) {
        UserEntity user = userRepository.getUserByName(username);
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(
                roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.setRoles(roles);
        userRepository.save(user);
    }


    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public CreateUserDsDto findByName(String name) {
        UserEntity userEntity = userRepository.getUserByName(name);
        return CreateUserDsDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .address(userEntity.getAddress())
                .phone(userEntity.getPhone())
                .website(userEntity.getWebsite())
                .company(userEntity.getCompany())
                .build();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public CreateUserDsDto save(CreateUserDsDto requestModel) {
        UserEntity user =
                UserEntity.builder()
                        .id(requestModel.getId())
                        .username(requestModel.getUsername())
                        .firstName(requestModel.getFirstName())
                        .lastName(requestModel.getLastName())
                        .password(requestModel.getPassword())
                        .email(requestModel.getEmail())
                        .address(requestModel.getAddress())
                        .phone(requestModel.getPhone())
                        .website(requestModel.getWebsite())
                        .company(requestModel.getCompany())
                        .build();
        userRepository.save(user);
        return  CreateUserDsDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .company(user.getCompany())
                .build();
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}




