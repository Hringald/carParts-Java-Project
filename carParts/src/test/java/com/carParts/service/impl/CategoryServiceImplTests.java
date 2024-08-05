package com.carParts.service.impl;

import com.carParts.model.entity.Category;
import com.carParts.repository.CategoryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class CategoryServiceImplTests {
    private final String TEST_NAME = "test";
    private static CategoryRepo mockCategoryRepo;
    private static CategoryServiceImpl toTest;

    @BeforeEach
    void setUp() {
        mockCategoryRepo = Mockito.mock();

        toTest = new CategoryServiceImpl(mockCategoryRepo);
    }

    @Test
    void getAllCategoriesReturnAList_HasCategories() {
        Category category = new Category();
        category.setName(TEST_NAME);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        when(mockCategoryRepo.findAll()).thenReturn(categories);

        Assertions.assertEquals(categories.size(), 1);
    }

    @Test
    void getAllCategoriesReturnAList_HasNoCategories() {
        var categories = toTest.getAllCategories();

        Assertions.assertEquals(categories.size(), 0);
    }
}
