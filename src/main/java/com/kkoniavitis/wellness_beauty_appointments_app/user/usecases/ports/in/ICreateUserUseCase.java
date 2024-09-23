package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.in;


import com.kkoniavitis.wellness_beauty_appointments_app.auth.domains.UserPrincipal;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserIdentityAvailabilityResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateInfoRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserRequestModel;

import java.util.List;

public interface ICreateUserUseCase {
    List<CreateUserSummaryResponseResource> getAllUsers();
    CreateUserSummaryResponseResource getCurrentUser(UserPrincipal currentUser);

    CreateUserIdentityAvailabilityResponseResource checkUsernameAvailability(String username);

    CreateUserIdentityAvailabilityResponseResource checkEmailAvailability(String email);

    CreateUserSummaryResponseResource getUserProfile(String username);

    CreateUserSummaryResponseResource addUser(CreateUserRequestModel user);

    CreateUserSummaryResponseResource updateUser(CreateUserRequestModel newUser, String username, UserPrincipal currentUser);

    void deleteUser(String username, UserPrincipal currentUser);

    void giveAdmin(String username);

    void removeAdmin(String username);

    CreateUserSummaryResponseResource setOrUpdateInfo(UserPrincipal currentUser, CreateInfoRequestModel infoRequest);

}
