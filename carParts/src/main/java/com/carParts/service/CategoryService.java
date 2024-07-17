package com.carParts.service;


import com.carParts.model.entity.Category;
import com.carParts.model.entity.CategoryEnum;

public interface CategoryService {

    void initCategories();

    Category findCategory(CategoryEnum category);

}
