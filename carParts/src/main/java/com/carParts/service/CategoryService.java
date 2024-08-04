package com.carParts.service;


import com.carParts.model.entity.Category;

import java.util.List;

public interface CategoryService {

    void initCategories();

    Category findCategory(String categoryName);

    List<Category> getAllCategories();

}
