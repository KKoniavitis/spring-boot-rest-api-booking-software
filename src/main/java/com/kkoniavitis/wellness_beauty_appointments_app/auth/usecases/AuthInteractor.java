package com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases;


import com.kkoniavitis.wellness_beauty_appointments_app.auth.adapters.out.presenters.view.resources.CreateJwtAuthenticationResponseResource;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.models.CreateLoginRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.models.CreateSignUpRequestModel;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.ports.in.IAuthGenericUseCase;
import com.kkoniavitis.wellness_beauty_appointments_app.auth.usecases.ports.out.IAuthViewPresenter;
import com.kkoniavitis.wellness_beauty_appointments_app.infrastructure.springsecurity.JwtService;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.AppException;
import com.kkoniavitis.wellness_beauty_appointments_app.shared.exceptions.BlogapiException;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.entities.UserEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleName;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.dtos.CreateUserDsDto;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IRoleDsGateway;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IUserDsGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthInteractor implements IAuthGenericUseCase {

    private static final String USER_ROLE_NOT_SET = "User role not set";
    private final IAuthViewPresenter authViewPresenter;
    private final IUserDsGateway userDsGateway;
    private final PasswordEncoder passwordEncoder;
    private final IRoleDsGateway roleDsGateway;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AuthInteractor.class);

    @Transactional
    @Override
    public CreateJwtAuthenticationResponseResource registerUser(CreateSignUpRequestModel signUpRequest) {
        if (Boolean.TRUE.equals(userDsGateway.existsByUsername(signUpRequest.getUsername()))) {
            throw new BlogapiException(HttpStatus.BAD_REQUEST, "Username is already taken");
        }
        if (Boolean.TRUE.equals(userDsGateway.existsByEmail(signUpRequest.getEmail()))) {
            throw new BlogapiException(HttpStatus.BAD_REQUEST, "Email is already taken");
        }

        // Prepare user details
        String firstName = signUpRequest.getFirstName().toLowerCase();
        String lastName = signUpRequest.getLastName().toLowerCase();
        String username = signUpRequest.getUsername().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();
        String password = passwordEncoder.encode(signUpRequest.getPassword());

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleDsGateway.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));

        // Assign admin role if it's the first user
        if (userDsGateway.count() == 0) {
            roles.add(roleDsGateway.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }


        UserEntity user = new UserEntity(firstName, lastName, username, email, password, roles);

        CreateUserDsDto createUserDsDto = CreateUserDsDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();

        logger.info("User created with role: " + createUserDsDto.getRoles());
        userDsGateway.save(createUserDsDto);

        logger.info("User saved with roles: " + createUserDsDto.getRoles());


        String jwt = jwtService.generateToken(user);
        return authViewPresenter.prepareSuccessView(jwt);
    }

    @Override
    public CreateJwtAuthenticationResponseResource authenticateUser(CreateLoginRequestModel loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserEntity user = userDsGateway.findByUsername(loginRequest.getUsername());
        logger.info("User roles: " + user.getRoles());
        String jwt = jwtService.generateToken(user);
        return authViewPresenter.prepareSuccessView(jwt);
    }


}
