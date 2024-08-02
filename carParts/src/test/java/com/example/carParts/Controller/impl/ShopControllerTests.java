package com.example.carParts.Controller.impl;

import com.carParts.controller.impl.PartControllerImpl;
import com.carParts.model.entity.*;
import com.carParts.service.impl.*;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class ShopControllerTests {

    private PartControllerImpl toTest;
    private LoggedUser mockLoggedUser;
    private OfferServiceImpl mockOfferService;
    private UserServiceImpl mockUserService;

    private PartServiceImpl mockPartService;

    private MakeServiceImpl mockMakeService;

    private CategoryServiceImpl mockCategoryService;

    private ModelServiceImpl mockModelService;

    private AdminUser mockAdminUser;

    @BeforeEach
    void setUp() {
        mockLoggedUser = Mockito.mock();
        mockUserService = Mockito.mock();
        mockPartService = Mockito.mock();
        mockMakeService = Mockito.mock();
        mockCategoryService = Mockito.mock();
        mockModelService = Mockito.mock();
        mockAdminUser = Mockito.mock();

        toTest = new PartControllerImpl(mockLoggedUser, mockUserService, mockPartService, mockMakeService, mockCategoryService, mockModelService, mockAdminUser);
    }

    @Test
    void myPartsReturnsCorrectViewWithModelIfUserIsLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.myParts(Mockito.mock());

        Assertions.assertEquals(result, "MyParts");
    }

    @Test
    void myPartsReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.myParts(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void chooseMakeReturnsCorrectViewWithModelIfUserIsLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.chooseMake(Mockito.mock());

        Assertions.assertEquals(result, "ChooseMake");
    }

    @Test
    void chooseMakeReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.chooseMake(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void addPartReturnsCorrectViewWithModelIfUserIsLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.addPart("", Mockito.mock());

        Assertions.assertEquals(result, "AddPart");
    }

    @Test
    void addPartReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.addPart("", Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void removePartByIdReturnsCorrectViewWithModelIfUserIsLogged() {

        Part part = new Part();

        User user = new User();

        part.setSeller(user);

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockAdminUser.isAdmin()).thenReturn(true);
        when(mockLoggedUser.getId()).thenReturn(null);

        when(mockPartService.findPartById(null)).thenReturn(part);

        String result = toTest.removePartById(null);

        Assertions.assertEquals(result, "redirect:/parts/myParts");
    }

    @Test
    void removePartByIdReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.removePartById(null);

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void editPartByIdReturnsCorrectViewWithModelIfUserIsLogged() {

        Part part = new Part();

        Make make = new Make();
        make.setName("test");

        Model model = new Model();
        model.setName("test");

        Category category = new Category();
        category.setName("test");


        part.setMake(make);
        part.setModel(model);
        part.setCategory(category);

        User user = new User();

        part.setSeller(user);

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockAdminUser.isAdmin()).thenReturn(true);
        when(mockLoggedUser.getId()).thenReturn(null);

        when(mockPartService.findPartById(null)).thenReturn(part);

        String result = toTest.editPartById(null, Mockito.mock());

        Assertions.assertEquals(result, "EditPart");
    }

    @Test
    void editPartByIdReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.editPartById(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }
}
