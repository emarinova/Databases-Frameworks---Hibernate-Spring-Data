package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Category;
import javadbfundametals.bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategoryIntoDb(Category category) {
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }
}
