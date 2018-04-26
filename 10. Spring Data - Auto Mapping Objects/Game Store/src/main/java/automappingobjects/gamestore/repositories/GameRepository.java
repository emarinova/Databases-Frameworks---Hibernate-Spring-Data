package automappingobjects.gamestore.repositories;

import automappingobjects.gamestore.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findById(int id);
    Game findByTitle(String title);
}
