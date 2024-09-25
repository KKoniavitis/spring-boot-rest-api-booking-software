package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases;


import com.kkoniavitis.wellness_beauty_appointments_app.auth.domains.UserPrincipal;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.AccessDeniedException;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.AppException;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.BadRequestException;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.BlogapiException;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.utils.AppConstants;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserIdentityAvailabilityResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.AddressEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.Company2Entity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.GeoEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleName;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos.CreateUserDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateInfoRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserSummaryResponseModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.in.ICreateUserUseCase;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IRoleDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IUserDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IUserViewPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserInteractor implements ICreateUserUseCase {
    final IUserDsGateway userDsGateway;
    final IUserViewPresenter userPresenter;
    final IRoleDsGateway roleDsGateway;

    public UserInteractor(IUserDsGateway userDsGateway, IUserViewPresenter userPresenter,
                          IRoleDsGateway roleDsGateway) {
        this.userDsGateway = userDsGateway;
        this.userPresenter = userPresenter;
        this.roleDsGateway = roleDsGateway;
    }


    @Override
    public List<CreateUserSummaryResponseResource> getAllUsers() {
        List<CreateUserDsDto> allUsers = userDsGateway.getAllUsers();
        List<CreateUserSummaryResponseModel> userSummaryResponseModels = new ArrayList<>();
        for (CreateUserDsDto user : allUsers) {
            CreateUserSummaryResponseModel userModel =
                    CreateUserSummaryResponseModel.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .build();
            userSummaryResponseModels.add(userModel);
        }
        return userPresenter.prepareSuccessView(userSummaryResponseModels);
    }

    @Override
    public CreateUserSummaryResponseResource getCurrentUser(UserPrincipal currentUser) {
        if (!userDsGateway.existsById(currentUser.getId())){
            return userPresenter.prepareFailView("User does not exist");
        }
        CreateUserDsDto userEntity = userDsGateway.getCurrentUser(currentUser);
        CreateUserSummaryResponseModel user =
                CreateUserSummaryResponseModel.builder()
                        .id(userEntity.getId())
                        .username(userEntity.getUsername())
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .build();
        return userPresenter.prepareSuccessView(user);
    }

    @Override
    public CreateUserIdentityAvailabilityResponseResource checkUsernameAvailability(String username) {
        Boolean isAvailable = !userDsGateway.existsByUsername(username);
        return new CreateUserIdentityAvailabilityResponseResource(isAvailable);
    }

    @Override
    public CreateUserIdentityAvailabilityResponseResource checkEmailAvailability(String email) {
        Boolean isAvailable = !userDsGateway.existsByEmail(email);
        return new CreateUserIdentityAvailabilityResponseResource(isAvailable);
    }

    @Override
    public CreateUserSummaryResponseResource getUserProfile(String username) {
        if (!userDsGateway.existsByUsername(username)){
            return userPresenter.prepareFailView("User does not exist");
        }
        CreateUserDsDto userEntity = userDsGateway.getUserProfile(username);
        CreateUserSummaryResponseModel user =
                CreateUserSummaryResponseModel.builder()
                        .id(userEntity.getId())
                        .username(userEntity.getUsername())
                        .firstName(userEntity.getFirstName())
                        .lastName(userEntity.getLastName())
                        .build();

        return userPresenter.prepareSuccessView(user);
    }

    @Override
    public CreateUserSummaryResponseResource addUser(CreateUserRequestModel user) {
        if (userDsGateway.existsByUsername(user.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }
        if (userDsGateway.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email is already taken");
        }

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(
                roleDsGateway.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));

        CreateUserDsDto createUserDsDto =
                CreateUserDsDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .address(user.getAddress())
                        .phone(user.getPhone())
                        .website(user.getWebsite())
                        .company(user.getCompany())
                        .roles(roles)
                        .build();

        userDsGateway.addUser(createUserDsDto);

        CreateUserSummaryResponseModel createUserSummaryResponseModel =
                CreateUserSummaryResponseModel.builder()
                        .id(createUserDsDto.getId())
                        .username(createUserDsDto.getUsername())
                        .firstName(createUserDsDto.getFirstName())
                        .lastName(createUserDsDto.getLastName())
                        .build();

        return userPresenter.prepareSuccessView(createUserSummaryResponseModel);
    }

    @Override
    public CreateUserSummaryResponseResource updateUser(CreateUserRequestModel newUser, String username, UserPrincipal currentUser) {
        CreateUserDsDto user = userDsGateway.findByName(username);
        if (user.getId().equals(currentUser.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setAddress(newUser.getAddress());
            user.setPhone(newUser.getPhone());
            user.setWebsite(newUser.getWebsite());
            user.setCompany(newUser.getCompany());
            CreateUserDsDto updatedUser = userDsGateway.updateUser(newUser, user.getUsername(), currentUser);
            CreateUserSummaryResponseModel createUserSummaryResponseModel =
                    CreateUserSummaryResponseModel.builder()
                            .id(updatedUser.getId())
                            .username(updatedUser.getUsername())
                            .firstName(updatedUser.getFirstName())
                            .lastName(updatedUser.getLastName())
                            .build();
            return userPresenter.prepareSuccessView(createUserSummaryResponseModel);
        }
        throw new BlogapiException(HttpStatus.UNAUTHORIZED, AppConstants.YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION);
    }

    @Override
    public void deleteUser(String username, UserPrincipal currentUser) {
        CreateUserDsDto user = userDsGateway.findByName(username);
        if (!user.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            throw new AccessDeniedException("You don't have permission to delete profile of: " + username);
        }
        userDsGateway.deleteUser(username, currentUser);
    }

    @Override
    public void giveAdmin(String username) {
        CreateUserDsDto user = userDsGateway.findByName(username);
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleDsGateway.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User role not set")));
        roles.add(
                roleDsGateway.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.setRoles(roles);
        userDsGateway.giveAdmin(username);
    }

    @Override
    public void removeAdmin(String username) {
        CreateUserDsDto user = userDsGateway.findByName(username);
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(
                roleDsGateway.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.setRoles(roles);
        userDsGateway.removeAdmin(username);
    }

    @Override
    public CreateUserSummaryResponseResource setOrUpdateInfo(UserPrincipal currentUser, CreateInfoRequestModel infoRequest) {
        CreateUserDsDto user = userDsGateway.findByName(currentUser.getUsername());
        GeoEntity geo = new GeoEntity(infoRequest.getLat(), infoRequest.getLng());
        AddressEntity address = new AddressEntity(infoRequest.getStreet(), infoRequest.getSuite(), infoRequest.getCity(),
                infoRequest.getZipcode(), geo);
        Company2Entity company = new Company2Entity(infoRequest.getCompanyName(), infoRequest.getCatchPhrase(), infoRequest.getBs());
        if (user.getId().equals(currentUser.getId())
                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
            user.setAddress(address);
            user.setCompany(company);
            user.setWebsite(infoRequest.getWebsite());
            user.setPhone(infoRequest.getPhone());
            CreateUserDsDto updatedUser = userDsGateway.save(user);

            CreateUserSummaryResponseModel createUserSummaryResponseModel =
                    CreateUserSummaryResponseModel.builder()
                            .id(updatedUser.getId())
                            .username(updatedUser.getUsername())
                            .firstName(updatedUser.getFirstName())
                            .lastName(updatedUser.getLastName())
                            .build();
            return userPresenter.prepareSuccessView(createUserSummaryResponseModel);
        }
        throw new AccessDeniedException("You don't have permission to update users profile");
    }
}



