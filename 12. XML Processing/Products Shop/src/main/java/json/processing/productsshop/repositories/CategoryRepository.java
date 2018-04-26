package json.processing.productsshop.repositories;

import json.processing.productsshop.models.entity.Category;
import json.processing.productsshop.models.view.category.Query3.CategoryProductsCountViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findById(long id);

    @Query("SELECT new json.processing.productsshop.models.view.category.Query3.CategoryProductsCountViewModel(c.name, COUNT(p.id), AVG(p.price), SUM(p.price))" +
            "FROM Category c, Product p " +
            "WHERE p MEMBER OF c.products " +
            "GROUP BY c.id " +
            "ORDER BY COUNT(p.id) DESC")
    List<CategoryProductsCountViewModel> caregoriesInfo();
}
