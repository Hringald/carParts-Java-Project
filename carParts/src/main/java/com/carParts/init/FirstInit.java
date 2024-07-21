package com.carParts.init;

import com.carParts.service.CategoryService;
import com.carParts.service.MakeService;
import com.carParts.service.ModelService;
import com.carParts.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;

    private final MakeService makeService;
    private final ModelService modelService;

    public FirstInit(CategoryService categoryService,
                     UserService userService, MakeService makeService, ModelService modelService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.makeService = makeService;
        this.modelService = modelService;
    }

    @Override
    public void run(String... args) {
        this.userService.initAdmin();
        this.userService.initTest();
        this.categoryService.initCategories();
        this.makeService.initMakes();
        this.modelService.initModels();;
    }
}
