package automappingobjects.gamestore.seed;

import automappingobjects.gamestore.model.entity.Role;
import automappingobjects.gamestore.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class RoleSeedExecutor {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleSeedExecutor(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void insertRoles() {
        if (this.roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            Role userRole = new Role();
            userRole.setName("USER");

            this.roleRepository.save(adminRole);
            this.roleRepository.save(userRole);
        }
    }
}
