package jsonprocessing.cardealer.repositories;

import jsonprocessing.cardealer.entity.model.Supplier;
import jsonprocessing.cardealer.entity.view.Query3.SupplierLocalViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{
    Supplier findById(long id);

    @Query("SELECT new jsonprocessing.cardealer.entity.view.Query3.SupplierLocalViewModel(s.id, s.name, count(p.id))" +
            "FROM Supplier s, Part p WHERE p MEMBER OF s.parts" +
            " AND s.importer = false " +
            "GROUP BY s.id")
    List<SupplierLocalViewModel> localSuppliers();
}
