package automappingobjects.gamestore.services.role;

import automappingobjects.gamestore.model.entity.Role;

public interface RoleService {

    Role getRoleByName(Roles role);

    void updateRole(Role role);

    enum Roles {
        ADMIN,USER
    }
}
