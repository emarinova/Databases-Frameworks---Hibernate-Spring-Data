package json.processing.productsshop.repositories;

import json.processing.productsshop.models.entity.Product;
import json.processing.productsshop.models.entity.User;
import json.processing.productsshop.models.view.product.ProductSoldViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);
    List<Product> findAllByPriceBetweenAndBuyerIsNull(BigDecimal from, BigDecimal to);
    List<Product> findAllBySellerAndBuyerNotNull(User seller);

    @Query("SELECT new json.processing.productsshop.models.view.product.ProductSoldViewModel(p.name, p.price)" +
            "FROM Product p WHERE p.seller.id =:userId")
    List<ProductSoldViewModel> findAllByOneUser(@Param("userId")Long userId);
}
