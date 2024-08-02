package com.example.carParts.Controller.impl;

import com.carParts.controller.impl.AccountControllerImpl;
import com.carParts.model.entity.User;
import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.Mockito.when;

public class AccountControllerTests {

    private AccountControllerImpl toTest;
    private LoggedUser mockLoggedUser;
    private AdminUser mockAdminUser;
    private UserServiceImpl mockUserService;

    private AdminServiceImpl mockAdminService;

    @BeforeEach
    void setUp() {
        mockLoggedUser = Mockito.mock();
        mockUserService = Mockito.mock();
        mockAdminService = Mockito.mock();
        mockAdminUser = Mockito.mock();

        toTest = new AccountControllerImpl(mockLoggedUser, mockUserService, mockAdminService, mockAdminUser);
    }

    @Test
    void changePhoneReturnsCorrectViewWithModelIfUserIsLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.changePhone(Mockito.mock());

        Assertions.assertEquals(result, "manage");
    }

    @Test
    void changePhoneReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.changePhone(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void emailChangeReturnsCorrectViewWithModelIfUserIsLogged() {

        User user = new User();
        user.setEmail("TEST@ABV.BG");

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockLoggedUser.getId()).thenReturn(user.getId());
        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));

        String result = toTest.emailChange(Mockito.mock());

        Assertions.assertEquals(result, "email");
    }

    @Test
    void emailChangeReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.emailChange(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void passwordChangeReturnsCorrectViewWithModelIfUserIsLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.passwordChange(Mockito.mock());

        Assertions.assertEquals(result, "Password");
    }

    @Test
    void passwordChangeReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.passwordChange(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void personalDataReturnsCorrectViewWithModelIfUserIsLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.personalData(Mockito.mock());

        Assertions.assertEquals(result, "PersonalData");
    }

    @Test
    void personalDataReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.personalData(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void deleteUserReturnsCorrectViewWithModelIfUserIsLogged() {

        User user = new User();
        user.setEmail("TEST@ABV.BG");

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockLoggedUser.getId()).thenReturn(user.getId());
        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));

        String result = toTest.deleteUser(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void deleteUserReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.deleteUser(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

    @Test
    void becomeAdminReturnsCorrectViewWithModelIfUserIsLogged() {

        User user = new User();
        user.setEmail("TEST@ABV.BG");

        when(mockLoggedUser.isLogged()).thenReturn(true);
        when(mockLoggedUser.getId()).thenReturn(user.getId());
        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));

        String result = toTest.becomeAdmin(Mockito.mock());

        Assertions.assertEquals(result, "BecomeAdmin");
    }

    @Test
    void becomeAdminReturnsCorrectViewWithModelIfUserIsNotLogged() {

        when(mockLoggedUser.isLogged()).thenReturn(false);

        String result = toTest.becomeAdmin(Mockito.mock());

        Assertions.assertEquals(result, "redirect:/");
    }

}
