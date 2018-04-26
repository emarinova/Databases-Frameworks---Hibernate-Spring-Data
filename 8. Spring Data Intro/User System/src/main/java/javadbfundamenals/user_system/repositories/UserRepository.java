package javadbfundamenals.user_system.repositories;

import javadbfundamenals.user_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByEmailEndingWith(String provider);
    List<User> findAllByLastTimeLoggedInBefore(Date date);
}
