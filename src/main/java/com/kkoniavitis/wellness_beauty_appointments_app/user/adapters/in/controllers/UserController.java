package com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.in.controllers;


import com.kkoniavitis.wellness_beauty_appointments_app.auth.domains.UserPrincipal;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserIdentityAvailabilityResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.presenters.view.resources.CreateUserSummaryResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateInfoRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.models.CreateUserRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.in.ICreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final ICreateUserUseCase createUserUseCase;

    public UserController(ICreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @GetMapping("/all")
    public List<CreateUserSummaryResponseResource> getAllUsers() {
        return createUserUseCase.getAllUsers();
    }
    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public CreateUserSummaryResponseResource getCurrentUser(UserPrincipal currentUser) {
        return createUserUseCase.getCurrentUser(currentUser);
    }

    @GetMapping("/checkUsernameAvailability")
    public CreateUserIdentityAvailabilityResponseResource checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return createUserUseCase.checkUsernameAvailability(username);
    }

    @GetMapping("/checkEmailAvailability")
    public CreateUserIdentityAvailabilityResponseResource checkEmailAvailability(@RequestParam(value = "email") String email) {
        return createUserUseCase.checkEmailAvailability(email);
    }

    @GetMapping("/{username}/profile")
    public CreateUserSummaryResponseResource getUserProfile(@PathVariable(value = "username") String username) {
        return createUserUseCase.getUserProfile(username);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CreateUserSummaryResponseResource addUser(@Valid @RequestBody CreateUserRequestModel user) {
        return createUserUseCase.addUser(user);
    }

    @PutMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public CreateUserSummaryResponseResource updateUser(@Valid @RequestBody CreateUserRequestModel newUser,
                                                        @PathVariable(value = "username") String username, UserPrincipal currentUser) {
        return createUserUseCase.updateUser(newUser, username, currentUser);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable(value = "username") String username,
                                                  UserPrincipal currentUser) {
        createUserUseCase.deleteUser(username, currentUser);
    }

    @PutMapping("/{username}/giveAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void giveAdmin(@PathVariable(name = "username") String username) {
        createUserUseCase.giveAdmin(username);
    }

    @PutMapping("/{username}/takeAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void takeAdmin(@PathVariable(name = "username") String username) {
        createUserUseCase.removeAdmin(username);
    }

    @PutMapping("/setOrUpdateInfo")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public CreateUserSummaryResponseResource setAddress(UserPrincipal currentUser,
                                                        @Valid @RequestBody CreateInfoRequestModel infoRequest) {
        return createUserUseCase.setOrUpdateInfo(currentUser, infoRequest);
    }

}
