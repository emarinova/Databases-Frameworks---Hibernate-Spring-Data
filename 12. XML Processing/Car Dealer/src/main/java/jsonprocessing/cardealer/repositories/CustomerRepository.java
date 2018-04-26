package jsonprocessing.cardealer.repositories;

import jsonprocessing.cardealer.entity.binding.CustomerCreateBindingModel;
import jsonprocessing.cardealer.entity.model.Customer;
import jsonprocessing.cardealer.entity.view.Query1.CustomerOrderedViewModel;
import jsonprocessing.cardealer.entity.view.Query5.CustomerSalesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findById(long id);

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query1.CustomerOrderedViewModel(c.id, c.name, c.birthDate, c.youngDriver)" +
            "FROM Customer c " +
            "ORDER BY c.birthDate, c.youngDriver DESC")
    List<CustomerOrderedViewModel> findAllOrdered();

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query5.CustomerSalesViewModel(cus.id, cus.name, COUNT(s.id)) " +
            "FROM Customer cus, Sale s WHERE s MEMBER OF cus.sales " +
            "GROUP BY cus")
    List<CustomerSalesViewModel> customersTotalSales();
}
