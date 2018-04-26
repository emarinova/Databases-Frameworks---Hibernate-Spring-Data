package jsonprocessing.cardealer.repositories;

import jsonprocessing.cardealer.entity.model.Customer;
import jsonprocessing.cardealer.entity.model.Sale;
import jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel(car.id, cus.name, s.discount, SUM(p.price))" +
            "FROM Sale s, Customer cus, Car car, Part p WHERE cus = s.customer AND car = s.car " +
            "AND p MEMBER OF car.parts " +
            "GROUP BY s")
    List<SaleDiscountViewModel> allSalesWithDiscount();
}
