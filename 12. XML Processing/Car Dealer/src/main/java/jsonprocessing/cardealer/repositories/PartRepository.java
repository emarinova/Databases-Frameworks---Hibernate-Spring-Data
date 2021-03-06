package jsonprocessing.cardealer.repositories;

import jsonprocessing.cardealer.entity.model.Car;
import jsonprocessing.cardealer.entity.model.Part;
import jsonprocessing.cardealer.entity.view.Query4.PartViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Part findById(long id);

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query4.PartViewModel(p.name, p.price)" +
            "FROM Part p, Car c WHERE p MEMBER of c.parts AND c.id = :car_id")
    Set<PartViewModel> partsForCar(@Param("car_id") long id);
}
