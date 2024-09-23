package com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.repositories;

import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(RoleName name);
}
