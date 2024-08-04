package com.carParts.Controller.impl;

import com.carParts.controller.impl.AdminControllerImpl;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.service.MakeService;
import com.carParts.service.impl.ModelServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.when;

public class AdminControllerTests {
    private final String TEST_EMAIL = "test@abv.bg";
    private final String TEST_PASSWORD = "1234";

    private final String TEST_NAME = "testName";

    private final String TEST_PART_DESCRIPTION = "testPartDescriptionTestPartDescription";

    private final int TEST_PART_QUANTITY = 2;
    private final Double TEST_PART_PRICE = 2.50;

    private final String TEST_IMAGE_URL = "testImageUrl";

    private AdminControllerImpl toTest;
    private ModelServiceImpl mockModelService;
    private UserServiceImpl mockUserService;

    private MakeService mockMakeService;

    @BeforeEach
    void setUp() {
        mockUserService = Mockito.mock();
        mockModelService = Mockito.mock();
        mockMakeService = Mockito.mock();

        toTest = new AdminControllerImpl(mockUserService, mockModelService, mockMakeService);
    }

    @Test
    void userPartsReturnsCorrectViewWithModel() {

        String result = toTest.usersParts(Mockito.mock());

        Assertions.assertEquals(result, "Admin/UsersParts");
    }

    @Test
    void editUserPartsReturnsCorrectViewWithModel() {


        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);

        Set<Part> parts = new HashSet<>();

        Part part = new Part();
        part.setSeller(user);
        part.setName(TEST_NAME);
        part.setQuantity(TEST_PART_QUANTITY);
        part.setDescription(TEST_PART_DESCRIPTION);
        part.setPrice(TEST_PART_PRICE);

        parts.add(part);

        user.setParts(parts);

        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));


        String result = toTest.editUserParts(null, Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditUserParts");
    }


    @Test
    void editModelsShouldReturnCorrectViewWithModel() {

        List<Model> models = new ArrayList<>();

        when(mockModelService.getAllModels()).thenReturn(models);

        String result = toTest.editModels(Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditModels");
    }


    @Test
    void editModelShouldReturnCorrectViewWithModel() {

        List<Make> makes = new ArrayList<>();

        Make make = new Make();
        make.setName(TEST_NAME);

        Model model = new Model();
        model.setName(TEST_NAME);
        model.setImageUrl(TEST_IMAGE_URL);
        model.setMake(make);

        when(mockMakeService.getAllMakes()).thenReturn(makes);
        when(mockModelService.findModelById(null)).thenReturn(model);

        String result = toTest.editModel(null, Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditModel");
    }


    @Test
    void deleteModelShouldReturnCorrectViewWithModel() {

        String result = toTest.deleteModel(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/admin/editModels");
    }


    @Test
    void addModelModelShouldReturnCorrectViewWithModel() {

        String result = toTest.addModel(Mockito.mock());

        Assertions.assertEquals(result, "Admin/AddModel");
    }


    @Test
    void editMakesModelShouldReturnCorrectViewWithModel() {

        String result = toTest.editMakes(Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditMakes");
    }


    @Test
    void editMakeModelShouldReturnCorrectViewWithModel() {

        Make make = new Make();
        make.setName(TEST_NAME);
        make.setImageUrl(TEST_IMAGE_URL);

        when(mockMakeService.findMakeById(null)).thenReturn(make);

        String result = toTest.editMake(null, Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditMake");
    }


    @Test
    void deleteMakeModelShouldReturnCorrectViewWithModel() {

        Make make = new Make();
        make.setName(TEST_NAME);
        make.setImageUrl(TEST_IMAGE_URL);

        when(mockMakeService.findMakeById(null)).thenReturn(make);

        String result = toTest.deleteMake(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/admin/editMakes");
    }


    @Test
    void AddMakeShouldReturnCorrectViewWithModel() {

        String result = toTest.addMake();

        Assertions.assertEquals(result, "Admin/AddMake");
    }
}
