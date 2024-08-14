package com.carParts.Controller.impl;

import com.carParts.controller.impl.HomeControllerImpl;
import com.carParts.service.impl.HomeServiceImpl;
import com.carParts.service.impl.MakeServiceImpl;
import com.carParts.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HomeControllerTests {

    private HomeControllerImpl toTest;

    private HomeServiceImpl mockHomeService;

    @BeforeEach
    void setUp() {
        mockHomeService = Mockito.mock();
        toTest = new HomeControllerImpl(mockHomeService);
    }

    @Test
    void indexViewReturnsCorrectViewWithModel() {

        String result = toTest.index(Mockito.mock());

        Assertions.assertEquals(result, "Index.html");
    }

}
