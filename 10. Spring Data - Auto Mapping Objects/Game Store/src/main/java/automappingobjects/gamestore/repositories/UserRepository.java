package automappingobjects.gamestore.repositories;

import automappingobjects.gamestore.model.dto.view.UserLogedInViewModel;
import automappingobjects.gamestore.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findById(int id);
}
