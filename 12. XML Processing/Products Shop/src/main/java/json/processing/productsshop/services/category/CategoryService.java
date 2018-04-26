package json.processing.productsshop.services.category;

import json.processing.productsshop.models.binding.category.CategoryCreateBindingModel;
import json.processing.productsshop.models.view.category.Query3.CategoriesViewModel;

public interface CategoryService {
    void save(CategoryCreateBindingModel model);
    void save(CategoryCreateBindingModel[] models);
    CategoriesViewModel categoriesInfo();
}
