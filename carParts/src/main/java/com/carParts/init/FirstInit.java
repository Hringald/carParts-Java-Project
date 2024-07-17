package com.carParts.init;

import com.carParts.service.CategoryService;
import com.carParts.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FirstInit implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;

    public FirstInit(CategoryService categoryService,
                     UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        this.userService.initAdmin();
        this.userService.initTest();
        this.categoryService.initCategories();
    }
}
