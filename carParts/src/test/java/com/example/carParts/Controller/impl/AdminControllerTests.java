package com.example.carParts.Controller.impl;

import com.carParts.controller.impl.AdminControllerImpl;
import com.carParts.model.entity.Make;
import com.carParts.model.entity.Model;
import com.carParts.model.entity.Part;
import com.carParts.model.entity.User;
import com.carParts.service.MakeService;
import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.service.impl.ModelServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.when;

public class AdminControllerTests {
    private final String TEST_EMAIL = "test@abv.bg";
    private final String TEST_PASSWORD = "1234";

    private AdminControllerImpl toTest;
    private LoggedUser mockLoggedUser;
    private AdminUser mockAdminUser;
    private ModelServiceImpl mockModelService;
    private UserServiceImpl mockUserService;

    private MakeService mockMakeService;
    private AdminServiceImpl mockAdminService;

    @BeforeEach
    void setUp() {
        mockLoggedUser = Mockito.mock();
        mockUserService = Mockito.mock();
        mockAdminUser = Mockito.mock();
        mockModelService = Mockito.mock();
        mockMakeService = Mockito.mock();
        mockAdminService = Mockito.mock();

        toTest = new AdminControllerImpl(mockLoggedUser, mockUserService, mockAdminService, mockAdminUser, mockModelService, mockMakeService);
    }

    @Test
    void userPartsReturnsCorrectViewWithModelIfUserIsAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(true);

        String result = toTest.usersParts(Mockito.mock());

        Assertions.assertEquals(result, "Admin/UsersParts");
    }

    @Test
    void userPartsReturnsCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.usersParts(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void editUserPartsReturnsCorrectViewWithModelIfUserIsAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(true);

        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);

        Set<Part> parts = new HashSet<>();

        Part part = new Part();
        part.setSeller(user);
        part.setName("Name");
        part.setQuantity(1);
        part.setDescription("testtesttesttesttesttest");
        part.setPrice(20.00);

        parts.add(part);

        user.setParts(parts);

        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));


        String result = toTest.editUserParts(null, Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditUserParts");
    }

    @Test
    void editUserPartsReturnsCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.editUserParts(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void editModelsShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        List<Model> models = new ArrayList<>();

        when(mockAdminUser.isAdmin()).thenReturn(true);
        when(mockModelService.getAllModels()).thenReturn(models);

        String result = toTest.editModels(Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditModels");
    }

    @Test
    void editModelsShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        List<Model> models = new ArrayList<>();

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.editModels(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void editModelShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        List<Make> makes = new ArrayList<>();

        Make make = new Make();
        make.setName("test");

        Model model = new Model();
        model.setName("name");
        model.setImageUrl("test");
        model.setMake(make);

        when(mockAdminUser.isAdmin()).thenReturn(true);
        when(mockMakeService.getAllMakes()).thenReturn(makes);
        when(mockModelService.findModelById(null)).thenReturn(model);

        String result = toTest.editModel(null, Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditModel");
    }

    @Test
    void editModelShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.editModel(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void deleteModelShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(true);

        String result = toTest.deleteModel(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/admin/editModels");
    }

    @Test
    void deleteModelShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.deleteModel(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void addModelModelShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(true);

        String result = toTest.addModel(Mockito.mock());

        Assertions.assertEquals(result, "Admin/AddModel");
    }

    @Test
    void addModelModelShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.addModel(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void editMakesModelShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(true);

        String result = toTest.editMakes(Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditMakes");
    }

    @Test
    void editMakesModelShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.editMakes(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void editMakeModelShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        Make make = new Make();
        make.setName("test");
        make.setImageUrl("test");

        when(mockAdminUser.isAdmin()).thenReturn(true);
        when(mockMakeService.findMakeById(null)).thenReturn(make);

        String result = toTest.editMake(null, Mockito.mock());

        Assertions.assertEquals(result, "Admin/EditMake");
    }

    @Test
    void editMakeModelShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.editMake(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }


    @Test
    void deleteMakeModelShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        Make make = new Make();
        make.setName("test");
        make.setImageUrl("test");

        when(mockAdminUser.isAdmin()).thenReturn(true);
        when(mockMakeService.findMakeById(null)).thenReturn(make);

        String result = toTest.deleteMake(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/admin/editMakes");
    }

    @Test
    void deleteMakeModelShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.deleteMake(null, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void AddMakeShouldReturnCorrectViewWithModelIfUserIsAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(true);

        String result = toTest.addMake();

        Assertions.assertEquals(result, "Admin/AddMake");
    }

    @Test
    void AddMakeShouldReturnCorrectViewWithModelIfUserIsNotAdmin() {

        when(mockAdminUser.isAdmin()).thenReturn(false);

        String result = toTest.addMake();

        Assertions.assertEquals(result, "redirect:/");
    }

}
