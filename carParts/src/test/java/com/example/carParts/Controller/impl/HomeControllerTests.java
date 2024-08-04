package com.example.carParts.Controller.impl;

import com.carParts.controller.impl.HomeControllerImpl;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HomeControllerTests {

    private HomeControllerImpl toTest;
    private UserServiceImpl mockUserService;

    private MakeServiceImpl mockMakeService;

    @BeforeEach
    void setUp() {
        mockUserService = Mockito.mock();
        mockMakeService = Mockito.mock();

        toTest = new HomeControllerImpl(mockUserService, mockMakeService);
    }

    @Test
    void indexReturnsCorrectViewWithModel() {

        //when(mockLoggedUser.isLogged()).thenReturn(true);

        String result = toTest.index(Mockito.mock());

        Assertions.assertEquals(result, "Index.html");
    }

}
