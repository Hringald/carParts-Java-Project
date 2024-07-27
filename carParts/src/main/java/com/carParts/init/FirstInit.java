package com.carParts.init;

import com.carParts.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;

    private final MakeService makeService;
    private final ModelService modelService;
    private final PartService partService;

    public FirstInit(CategoryService categoryService,
                     UserService userService, MakeService makeService, ModelService modelService, PartService partService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.makeService = makeService;
        this.modelService = modelService;
        this.partService = partService;
    }

    @Override
    public void run(String... args) {
        this.userService.initAdmin();
        this.userService.initTest();
        this.categoryService.initCategories();
        this.makeService.initMakes();
        this.modelService.initModels();
        this.partService.initParts();
    }
}
