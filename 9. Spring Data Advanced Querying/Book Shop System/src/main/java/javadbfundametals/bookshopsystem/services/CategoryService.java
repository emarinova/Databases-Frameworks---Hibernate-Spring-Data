package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Category;

import java.util.List;

public interface CategoryService {

    void saveCategoryIntoDb(Category category);

    List<Category> getCategories();
}
