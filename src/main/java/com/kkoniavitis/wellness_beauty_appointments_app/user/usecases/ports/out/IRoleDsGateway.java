package com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out;


import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleName;

import java.util.Optional;

public interface IRoleDsGateway {
    Optional<RoleEntity> findByName(RoleName name);

    void save(RoleEntity roleEntity);
}
