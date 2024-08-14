
package com.carParts.Controller.impl;

import com.carParts.controller.impl.PartControllerImpl;
import com.carParts.model.entity.*;
import com.carParts.service.impl.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

public class PartControllerTests {

    private final String TEST_NAME = "testName";
    private PartControllerImpl toTest;
    private UserServiceImpl mockUserService;

    private PartServiceImpl mockPartService;

    private MakeServiceImpl mockMakeService;

    private CategoryServiceImpl mockCategoryService;

    private ModelServiceImpl mockModelService;
    private UserDetails mockUserDetails;


    @BeforeEach
    void setUp() {
        mockUserService = Mockito.mock();
        mockPartService = Mockito.mock();
        mockMakeService = Mockito.mock();
        mockCategoryService = Mockito.mock();
        mockModelService = Mockito.mock();
        mockUserDetails = Mockito.mock();

        toTest = new PartControllerImpl(mockUserService, mockPartService, mockMakeService, mockCategoryService, mockModelService);
    }

    @Test
    void myPartsReturnsCorrectViewWithModel() {


        String result = toTest.myParts(mockUserDetails, Mockito.mock());

        Assertions.assertEquals(result, "Part/MyParts");
    }


    @Test
    void chooseMakeReturnsCorrectViewWithModel() {

        String result = toTest.chooseMake(Mockito.mock());

        Assertions.assertEquals(result, "Part/ChooseMake");
    }


    @Test
    void addPartReturnsCorrectViewWithModel() {

        String result = toTest.addPart("", Mockito.mock());

        Assertions.assertEquals(result, "Part/AddPart");
    }


    @Test
    void removePartByIdReturnsCorrectViewWithModel() {

        Part part = new Part();

        User user = new User();

        part.setSeller(user);


        when(mockPartService.findPartById(null)).thenReturn(part);

        String result = toTest.removePartById(mockUserDetails, null);

        Assertions.assertEquals(result, "redirect:/parts/myParts");
    }

}
