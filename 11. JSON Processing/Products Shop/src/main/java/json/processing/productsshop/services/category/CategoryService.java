package json.processing.productsshop.services.category;

import json.processing.productsshop.models.binding.category.CategoryCreateBindingModel;
import json.processing.productsshop.models.view.category.CategoryProductsCountViewModel;

import java.util.List;

public interface CategoryService {
    void save(CategoryCreateBindingModel model);
    void save(CategoryCreateBindingModel[] models);
    List<CategoryProductsCountViewModel> categoriesInfo();
}
