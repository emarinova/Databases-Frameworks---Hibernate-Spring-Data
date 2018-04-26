package automappingobjects.gamestore.services.role;

import automappingobjects.gamestore.model.entity.Role;
import automappingobjects.gamestore.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role getRoleByName(Roles role) {
        return this.roleRepository.findByName(role.name());
    }

    @Override
    public void updateRole(Role role) {
        this.roleRepository.save(role);
    }
}
