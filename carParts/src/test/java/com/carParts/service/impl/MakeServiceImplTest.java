package com.carParts.service.impl;

import com.carParts.model.entity.Make;
import com.carParts.repository.MakeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;

class MakeServiceImplTest {

    private final String TEST_MAKE_NAME = "test";
    private final String TEST_INVALID_MAKE_NAME = "1234";

    private final Long TEST_INVALID_MAKE_ID = null;

    private MakeServiceImpl toTest;

    private MakeRepo mockMakeRepo;

    private PartServiceImpl mockPartService;

    private ModelServiceImpl mockModelService;

    @BeforeEach
    void setUp() {
        mockMakeRepo = Mockito.mock();
        mockPartService = Mockito.mock();
        mockModelService = Mockito.mock();


        toTest = new MakeServiceImpl(mockMakeRepo, mockPartService, mockModelService);
    }

    @Test
    void testFindMakeByName_MakeFound() {

        Make make = new Make();
        make.setName(TEST_MAKE_NAME);

        when(mockMakeRepo.findByName(TEST_MAKE_NAME)).thenReturn(Optional.of(make));

        Make expectedMake = this.toTest.findMakeByName(TEST_MAKE_NAME);

        Assertions.assertInstanceOf(Make.class, expectedMake);
        Assertions.assertEquals(expectedMake.getName(), TEST_MAKE_NAME);
    }

    @Test
    void testFindMakeByName_MakeNotFound() {
        Make expectedMake = this.toTest.findMakeByName(TEST_INVALID_MAKE_NAME);

        Assertions.assertNull(expectedMake);
    }

    @Test
    void testFindMakeById_MakeFound() {

        Make make = new Make();

        when(mockMakeRepo.findById(make.getId())).thenReturn(Optional.of(make));

        Make expectedMake = this.toTest.findMakeById(make.getId());

        Assertions.assertInstanceOf(Make.class, expectedMake);
        Assertions.assertEquals(expectedMake.getId(), make.getId());
    }

    @Test
    void testFindMakeById_MakeNotFound() {

        Make expectedMake = this.toTest.findMakeById(TEST_INVALID_MAKE_ID);

        Assertions.assertNull(expectedMake);
    }
}
