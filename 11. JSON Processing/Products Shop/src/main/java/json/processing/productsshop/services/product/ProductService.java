package json.processing.productsshop.services.product;

import json.processing.productsshop.models.binding.product.ProductCreateBindingModel;
import json.processing.productsshop.models.view.product.ProductInPriceRangeViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void save(ProductCreateBindingModel model);
    void save(ProductCreateBindingModel[] models);
    List<ProductInPriceRangeViewModel> getProductsInRangeWithNoBuyer(BigDecimal from, BigDecimal to);
}
