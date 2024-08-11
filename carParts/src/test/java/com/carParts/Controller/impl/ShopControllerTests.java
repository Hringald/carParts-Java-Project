package com.carParts.Controller.impl;

import com.carParts.controller.impl.ShopControllerImpl;
import com.carParts.model.entity.*;
import com.carParts.service.impl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ShopControllerTests {

    private final String TEST_NAME = "testName";
    private ShopControllerImpl toTest;

    private PartServiceImpl mockPartService;

    private MakeServiceImpl mockMakeService;

    private CategoryServiceImpl mockCategoryService;

    private ModelServiceImpl mockModelService;


    @BeforeEach
    void setUp() {
        mockPartService = Mockito.mock();
        mockMakeService = Mockito.mock();
        mockCategoryService = Mockito.mock();
        mockModelService = Mockito.mock();

        toTest = new ShopControllerImpl(mockMakeService, mockModelService, mockCategoryService, mockPartService);
    }

    @Test
    void shopModelsReturnsCorrectViewWithModel() {


        String result = toTest.shopModels(TEST_NAME, Mockito.mock());

        Assertions.assertEquals(result, "Shop/ShopModels");
    }


    @Test
    void shopCategoriesReturnsCorrectViewWithModel() {

        String result = toTest.shopCategories(TEST_NAME, TEST_NAME, Mockito.mock());

        Assertions.assertEquals(result, "Shop/ShopCategories");
    }


    @Test
    void findPaginatedReturnsCorrectViewWithModel() {

        Page<Part> page = Mockito.mock();

        Part part = new Part();
        part.setName(TEST_NAME);

        List<Part> parts = new ArrayList<>();
        parts.add(part);

        String mockSearchTerm = "%" + TEST_NAME + "%";

        when(mockPartService.findPaginated(1, 6, TEST_NAME, TEST_NAME, TEST_NAME, mockSearchTerm)).thenReturn(page);
        when(page.getContent()).thenReturn(parts);

        String result = toTest.findPaginated(TEST_NAME, TEST_NAME, TEST_NAME, 1, TEST_NAME, Mockito.mock());

        Assertions.assertEquals(result, "Shop/ShopPage.html");
    }
}
