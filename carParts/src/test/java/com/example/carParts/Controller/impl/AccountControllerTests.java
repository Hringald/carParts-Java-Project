package com.example.carParts.Controller.impl;

import com.carParts.controller.impl.AccountControllerImpl;
import com.carParts.model.CarPartsUserDetails;
import com.carParts.model.entity.User;
import com.carParts.repository.UserRepo;
import com.carParts.service.impl.AdminServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static org.mockito.Mockito.when;

public class AccountControllerTests {
    private final String TEST_EMAIL = "test@abv.bg";
    private final String TEST_PHONE = "1234784512";
    private final String TEST_PASSWORD = "testPassword";
    private AccountControllerImpl toTest;
    private UserServiceImpl mockUserService;

    private AdminServiceImpl mockAdminService;

    private UserDetails mockUserDetails;

    private UserRepo mockUserRepo;

    @BeforeEach
    void setUp() {
        mockUserService = Mockito.mock();
        mockAdminService = Mockito.mock();
        mockUserDetails = Mockito.mock();
        mockUserRepo = Mockito.mock();

        toTest = new AccountControllerImpl(mockUserService, mockAdminService);
    }

    @Test
    void changePhoneReturnsCorrectViewWithModel() {

        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPhone(TEST_PHONE);

        when(mockUserRepo.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));
        when(mockUserDetails.getUsername()).thenReturn(TEST_EMAIL);
        when(mockUserService.findUserByEmail(TEST_EMAIL)).thenReturn(user);

        String result = toTest.changePhone(mockUserDetails, Mockito.mock());

        Assertions.assertEquals(result, "Account/Manage");
    }


    @Test
    void emailChangeReturnsCorrectViewWithModel() {

        User user = new User();
        user.setEmail(TEST_EMAIL);

        when(mockUserDetails.getUsername()).thenReturn(TEST_EMAIL);
        when(mockUserService.findUserByEmail(TEST_EMAIL)).thenReturn(user);

        String result = toTest.emailChange(mockUserDetails, Mockito.mock());

        Assertions.assertEquals(result, "Account/Email");
    }

    @Test
    void passwordChangeReturnsCorrectViewWithModel() {
        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);

        when(mockUserDetails.getUsername()).thenReturn(TEST_EMAIL);
        when(mockUserService.findUserByEmail(TEST_EMAIL)).thenReturn(user);

        String result = toTest.passwordChange(Mockito.mock());

        Assertions.assertEquals(result, "Account/Password");
    }

    @Test
    void personalDataReturnsCorrectViewWithModel() {

        User user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);

        when(mockUserDetails.getUsername()).thenReturn(TEST_EMAIL);
        when(mockUserService.findUserByEmail(TEST_EMAIL)).thenReturn(user);

        String result = toTest.personalData(Mockito.mock());

        Assertions.assertEquals(result, "Account/PersonalData");
    }
    @Test
    void deleteUserReturnsCorrectViewWithModel() {

        User user = new User();
        user.setEmail(TEST_EMAIL);

        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));
        when(mockUserDetails.getUsername()).thenReturn(TEST_EMAIL);
        when(mockUserService.findUserByEmail(TEST_EMAIL)).thenReturn(user);

        String result = toTest.deleteUser(mockUserDetails, Mockito.mock());

        Assertions.assertEquals(result, "redirect:/identity/account/logout");
    }

    @Test
    void becomeAdminReturnsCorrectViewWithModel() {

        User user = new User();
        user.setEmail(TEST_EMAIL);

        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));
        when(mockUserDetails.getUsername()).thenReturn(TEST_EMAIL);
        when(mockUserService.findUserByEmail(TEST_EMAIL)).thenReturn(user);
        when(mockUserService.findUserById(null)).thenReturn(Optional.of(user));

        String result = toTest.becomeAdmin(Mockito.mock());

        Assertions.assertEquals(result, "Account/BecomeAdmin");
    }

}