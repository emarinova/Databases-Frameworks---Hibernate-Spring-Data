package json.processing.productsshop.services.product;

import json.processing.productsshop.models.binding.product.ProductCreateBindingModel;
import json.processing.productsshop.models.view.product.Query1.ProductInRange;

import java.math.BigDecimal;

public interface ProductService {
    void save(ProductCreateBindingModel model);
    void save(ProductCreateBindingModel[] models);
    ProductInRange getProductsInRangeWithNoBuyer(BigDecimal from, BigDecimal to);
}
