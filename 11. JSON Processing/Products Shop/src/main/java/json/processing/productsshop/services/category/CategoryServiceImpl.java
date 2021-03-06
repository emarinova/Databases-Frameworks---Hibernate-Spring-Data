package json.processing.productsshop.services.category;

import json.processing.productsshop.models.binding.category.CategoryCreateBindingModel;
import json.processing.productsshop.models.entity.Category;
import json.processing.productsshop.models.view.category.CategoryProductsCountViewModel;
import json.processing.productsshop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CategoryCreateBindingModel model) {
        Category category = modelMapper.map(model, Category.class);
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public void save(CategoryCreateBindingModel[] models) {
        for (CategoryCreateBindingModel model : models){
            this.save(model);
        }
    }

    @Override
    public List<CategoryProductsCountViewModel> categoriesInfo(){
        return this.categoryRepository.caregoriesInfo();
    }
}
