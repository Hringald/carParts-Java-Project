package com.carParts.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HomeServiceImplTest {

    private final String TEST_MAKE_NAME = "test";
    private final String TEST_NEW_MAKE_NAME = "test123";
    private final String TEST_INVALID_MAKE_NAME = "1234";

    private final Long TEST_INVALID_MAKE_ID = null;

    private static MakeServiceImpl makeService;
    private HomeServiceImpl toTest;


    @BeforeEach
    void setUp() {
        makeService = Mockito.mock();

        toTest = new HomeServiceImpl(makeService);
    }

    @Test
    void indexViewReturnsModelWorks() {

        Assertions.assertDoesNotThrow(() -> this.toTest.indexView(Mockito.mock()));
    }

}
