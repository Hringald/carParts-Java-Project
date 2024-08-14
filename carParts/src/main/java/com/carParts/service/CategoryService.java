package com.carParts.service;


import com.carParts.model.entity.Category;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CategoryService {
    
    void initCategories();

    Category findCategory(String categoryName);

    List<Category> getAllCategories();

    void shopCategoriesView(String makeName, String modelName, Model model);
}
