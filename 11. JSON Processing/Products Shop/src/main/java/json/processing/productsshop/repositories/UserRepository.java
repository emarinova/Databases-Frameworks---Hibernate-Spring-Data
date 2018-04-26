package json.processing.productsshop.repositories;

import json.processing.productsshop.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    List<User> findAllDistinctByProductsSoldIsNotNull();
}
