package com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.repositories;

import com.kkoniavitis.wellness_beauty_appointments_app.auth.domains.UserPrincipal;
import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.entities.UserEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    @EntityGraph(attributePaths = "roles")
    UserEntity findByUsername(@NotBlank String username);

    Boolean existsByUsername(@NotBlank String username);

    Boolean existsByEmail(@NotBlank String email);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    default UserEntity getUser(UserPrincipal currentUser) {
        return getUserByName(currentUser.getUsername());
    }

    default UserEntity getUserByName(String username) {
        return findByUsername(username);
    }

}
