package com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds;

import com.kkoniavitis.wellness_beauty_appointments_app.user.adapters.out.gateways.ds.repositories.IRoleRepository;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleEntity;
import com.kkoniavitis.wellness_beauty_appointments_app.user.domains.role.RoleName;
import com.kkoniavitis.wellness_beauty_appointments_app.user.usecases.ports.out.IRoleDsGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleDsGateway implements IRoleDsGateway {
    private final IRoleRepository roleRepository;

    public RoleDsGateway(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<RoleEntity> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void save(RoleEntity roleEntity) {
        roleRepository.save(roleEntity);
    }
}
