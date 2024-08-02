package com.example.carParts.Controller.impl;

import com.carParts.controller.impl.HomeControllerImpl;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import com.carParts.util.AdminUser;
import com.carParts.util.LoggedUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.mockito.Mockito.when;

public class HomeControllerTests {

    private HomeControllerImpl toTest;
    private LoggedUser mockLoggedUser;
    private AdminUser mockAdminUser;
    private UserServiceImpl mockUserService;

    private MakeServiceImpl mockMakeService;

    @BeforeEach
    void setUp() {
        mockLoggedUser = Mockito.mock();
        mockUserService = Mockito.mock();
        mockAdminUser = Mockito.mock();
        mockMakeService = Mockito.mock();

        toTest = new HomeControllerImpl(mockLoggedUser, mockUserService, mockAdminUser, mockMakeService);
    }

    @Test
    void indexReturnsCorrectViewWithModel() {

        when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.index(Mockito.mock());

        Assertions.assertEquals(result, "index.html");
    }

}
