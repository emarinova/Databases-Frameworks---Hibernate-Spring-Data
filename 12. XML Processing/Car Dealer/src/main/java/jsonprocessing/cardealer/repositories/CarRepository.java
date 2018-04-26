package jsonprocessing.cardealer.repositories;

import jsonprocessing.cardealer.entity.model.Car;
import jsonprocessing.cardealer.entity.view.Query2.CarViewModel;
import jsonprocessing.cardealer.entity.view.Query4.CarWithPartsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);

    @Query("SELECT  new jsonprocessing.cardealer.entity.view.Query2.CarViewModel(c.id, c.make, c.model, c.travelledDistance)" +
            "FROM Car c " +
            "WHERE c.make = :make " +
            "ORDER BY c.model, c.travelledDistance DESC")
    List<CarViewModel> findAllByMake(@Param("make")String make);

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query4.CarWithPartsViewModel(c.id, c.make, c.model, c.travelledDistance)" +
            "FROM Car c")
    List<CarWithPartsViewModel> carsWithParts();
}
