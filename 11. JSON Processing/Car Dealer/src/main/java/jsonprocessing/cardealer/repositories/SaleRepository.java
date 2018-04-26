package jsonprocessing.cardealer.repositories;

import jsonprocessing.cardealer.entity.model.Customer;
import jsonprocessing.cardealer.entity.model.Sale;
import jsonprocessing.cardealer.entity.view.Query1.SaleViewModel;
import jsonprocessing.cardealer.entity.view.Query5.SalePrice;
import jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query1.SaleViewModel(c.model)" +
            "FROM Sale s, Car c " +
            "WHERE s.car = c " +
            "AND s.customer = :customer")
    Set<SaleViewModel> findByCustomerId(@Param("customer")Customer customer);

   // @Query("SELECT new jsonprocessing.cardealer.entity.view.Query5.SalePrice(cus.id, s.id, (SUM(p.price)*(100-s.discount)/100))" +
   //         "FROM Sale s, Car c, Customer cus, Part p WHERE c = s.car AND cus = s.customer AND p MEMBER OF c.parts " +
   //         "GROUP BY s " +
   //         "HAVING cus = :cus" )
   // List<SalePrice> salesWithPrices(@Param("cus")Customer customer);

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query6.SaleDiscountViewModel(car.id, cus.name, s.discount, SUM(p.price))" +
            "FROM Sale s, Customer cus, Car car, Part p WHERE cus = s.customer AND car = s.car " +
            "AND p MEMBER OF car.parts " +
            "GROUP BY s")
    List<SaleDiscountViewModel> allSalesWithDiscount();
}
