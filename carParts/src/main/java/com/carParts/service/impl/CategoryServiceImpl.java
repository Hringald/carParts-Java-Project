package com.carParts.service.impl;

import com.carParts.model.entity.Category;
import com.carParts.model.entity.CategoryEnum;
import com.carParts.repository.CategoryRepo;
import com.carParts.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepo.count() != 0) {
            return;
        }

        Arrays.stream(CategoryEnum.values())
                .forEach(s -> {
                    Category category = new Category();
                    category.setName(String.valueOf(s));
                    switch (s.getValue()) {
                        case "Main Parts":
                            category.setImageUrl("MainCarParts.jpg");
                            break;
                        case "Electronics":
                            category.setImageUrl("CarElectronics.jpg");
                            break;
                        case "Interior":
                            category.setImageUrl("CarInterior.jpg");
                            break;
                        case "Power-train and chassis":
                            category.setImageUrl("powerTrainAndChassis.jpg");
                            break;
                        default:
                            category.setImageUrl("Miscellaneous.jpg");
                            break;
                    }

                    this.categoryRepo.save(category);
                });

    }

    @Override
    public Category findCategory(CategoryEnum categoryEnum) {
        return this.categoryRepo.findByName(categoryEnum).orElseThrow();
    }

}
